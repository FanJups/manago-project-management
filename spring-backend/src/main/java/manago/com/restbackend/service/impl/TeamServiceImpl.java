package manago.com.restbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Employee;
import manago.com.restbackend.model.Team;
import manago.com.restbackend.repository.EmployeeRepository;
import manago.com.restbackend.repository.ProjectRepository;
import manago.com.restbackend.repository.ResourceRepository;
import manago.com.restbackend.repository.TeamRepository;
import manago.com.restbackend.service.TeamService;
import manago.com.restbackend.shared.request.TeamRequest;
import manago.com.restbackend.shared.response.TeamResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private EmployeeRepository employeeRepository;
    private ResourceRepository resourceRepository;
    private ProjectRepository projectRepository;
    private ManagoMapper mapper;

    public TeamServiceImpl (TeamRepository teamRepository, EmployeeRepository employeeRepository, ResourceRepository resourceRepository, ProjectRepository projectRepository, ManagoMapper mapper) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.resourceRepository = resourceRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TeamResponse> all() {
        return teamRepository
                .findAll()
                .stream()
                .map(mapper::teamToTeamResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TeamResponse one(String name) {
        Team team = teamRepository.findByName(name);
        if (team == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        else return mapper.teamToTeamResponse(team);
    }

    private Team teamSetter(Team team, TeamRequest request) {
        request.getEmployeeRequests()
                .forEach(r -> team.addEmployee(employeeRepository.findByEmployeeId(r.getEmployeeId())));
        request.getResourceRequests()
                .forEach(r -> team.addResource(resourceRepository.findByResourceId(r.getResourceId())));
        return team;
    }

    @Override
    public TeamResponse updateTeam(String name, TeamRequest request) {
        Team team = teamRepository.findByName(name);
        if (team == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_UPDATED.getErrorMessage());
        else {
            team.setSize(request.getSize());
            team.setMonthlyCost(request.getMonthlyCost());

            //todo

            teamRepository.save(team);
            return mapper.teamToTeamResponse(team);
        }
    }

    @Override
    public TeamResponse createTeam(TeamRequest request) {
        if (teamRepository.findByName(request.getName()) != null)
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        else {
            Team team = mapper.teamRequestToTeam(request);

            request.getEmployeeRequests()
                    .forEach(r -> {
                        log.info("Employee " + r.getEmployeeId());
                        Employee employee = employeeRepository.findByEmployeeId(r.getEmployeeId());
                        log.info("First name: " + employee.getFirstName());

                        //todo Ta linia poniżej nie działa

                        team.getEmployees().add(employee);
                    });
            request.getResourceRequests()
                    .forEach(r -> team.addResource(resourceRepository.findByResourceId(r.getResourceId())));

            teamRepository.save(team);
            return mapper.teamToTeamResponse(team);
        }
    }

    @Override
    @Transactional
    public void deleteTeam(String name) {
        Team team = teamRepository.findByName(name);
        if (projectRepository.findAllByTeam(team).size() != 0)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_DELETED.getErrorMessage());
        else {
            projectRepository.findAllByTeam(team)
                    .forEach(project -> {
                        project.setTeam(null);
                        projectRepository.save(project);
                    });
            team.setEmployees(null);
            team.setResources(null);
            teamRepository.save(team);

            teamRepository.deleteByName(name);
        }
    }
}
