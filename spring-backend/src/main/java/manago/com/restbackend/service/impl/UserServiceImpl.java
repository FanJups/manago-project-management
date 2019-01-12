package manago.com.restbackend.service.impl;

import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Employee;
import manago.com.restbackend.model.User;
import manago.com.restbackend.repository.EmployeeRepository;
import manago.com.restbackend.repository.UserRepository;
import manago.com.restbackend.service.UserService;
import manago.com.restbackend.shared.request.UserRequest;
import manago.com.restbackend.shared.response.UserResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private ManagoMapper mapper;

    public UserServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository, ManagoMapper mapper) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<UserResponse> all() {
        return userRepository
                .findAll()
                .stream()
                .map(mapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse one(String username) {
        return mapper.userToUserResponse(userRepository.findByUsername(username));
    }

    public UserResponse update(String username, UserRequest request) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());

        if(request.getEmail() == null)
            throw new RuntimeException(ErrorMessages.MISSING_FIELD.getErrorMessage());
        user.setEmail(request.getEmail());

        Employee employee = employeeRepository.findByEmployeeId(request.getEmployeeId());
        user.setEmployee(employee);

        userRepository.save(user);

        return mapper.userToUserResponse(user);
    }

    public UserResponse create(UserRequest request) {
        User user = mapper.userRequestToUser(request);

        Employee employee = employeeRepository.findByEmployeeId(request.getEmployeeId());
        user.setEmployee(employee);

        userRepository.save(user);

        return mapper.userToUserResponse(user);
    }

    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.RECORD_NOT_DELETED.getErrorMessage());
        }
    }
}
