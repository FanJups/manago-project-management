package manago.com.restbackend.service.impl;

import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Employee;
import manago.com.restbackend.repository.EmployeeRepository;
import manago.com.restbackend.repository.TeamRepository;
import manago.com.restbackend.repository.UserRepository;
import manago.com.restbackend.service.EmployeeService;
import manago.com.restbackend.shared.request.EmployeeRequest;
import manago.com.restbackend.shared.response.EmployeeResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private TeamRepository teamRepository;
    private UserRepository userRepository;
    private ManagoMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TeamRepository teamRepository, UserRepository userRepository, ManagoMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EmployeeResponse> all() {
        return employeeRepository
                .findAll()
                .stream()
                .map(mapper::employeeToEmployeeResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse one(Long id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        else return mapper.employeeToEmployeeResponse(employee);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        else {
            employee.setEmploymentType(request.getEmploymentType());
            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setSalary(request.getSalary());

            employeeRepository.save(employee);
            return mapper.employeeToEmployeeResponse(employee);
        }
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if(employeeRepository.findByEmployeeId(request.getEmployeeId()) != null)
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        Employee employee = mapper.employeeRequestToEmployee(request);
        employeeRepository.save(employee);
        return mapper.employeeToEmployeeResponse(employee);
    }

    public void delete(Long id) {
        if (employeeRepository.findByEmployeeId(id) == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_DELETED.getErrorMessage());
        //Delete all information about employee in teams
        teamRepository.findAll()
                .forEach(team -> team.deleteEmployee(employeeRepository.findByEmployeeId(id)));

        //Delete all information about employee in users
        userRepository.findAllByEmployee(employeeRepository.findByEmployeeId(id))
                .forEach(user -> {
                    user.setEmployee(null);
                    userRepository.save(user);
                });
        employeeRepository.deleteById(id);
    }
}
