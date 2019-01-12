package manago.com.restbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Employee;
import manago.com.restbackend.model.Resource;
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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public TeamResponse update(String name, TeamRequest request) {
        Team team = teamRepository.findByName(name);

        if (team == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());

        else {
            if(request.getSize() == null)
                throw new RuntimeException(ErrorMessages.MISSING_FIELD.getErrorMessage());
            else
                team.setSize(request.getSize());
            if(request.getMonthlyCost() == null)
                throw new RuntimeException(ErrorMessages.MISSING_FIELD.getErrorMessage());
            else
                team.setMonthlyCost(request.getMonthlyCost());

            resetTeamEmployees(team);
            getEmployeesByIds(request.getEmployeeIds()).forEach(team::addEmployee);
            resetTeamResources(team);
            getResourcesByIds(request.getResourceIds()).forEach(team::addResource);

            try {
                teamRepository.save(team);
            } catch (Exception e) {
                throw new RuntimeException(ErrorMessages.RECORD_NOT_UPDATED.getErrorMessage());
            }

            return mapper.teamToTeamResponse(team);
        }
    }

    @Override
    public TeamResponse create(TeamRequest request) {

        if (teamRepository.findByName(request.getName()) != null)
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        else {
            Team team = mapper.teamRequestToTeam(request);

            getEmployeesByIds(request.getEmployeeIds()).forEach(team::addEmployee);
            getResourcesByIds(request.getResourceIds()).forEach(team::addResource);

            teamRepository.save(team);
            return mapper.teamToTeamResponse(team);
        }
    }

    @Override
    @Transactional
    public void delete(String name) {
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

    private Set<Employee> getEmployeesByIds(Set<Long> ids) {
        return ids.stream()
                .distinct()
                .map(employeeRepository::findByEmployeeId)
                .collect(Collectors.toSet());
    }

    private void resetTeamEmployees(Team team) {
        for (Iterator<Employee> iter = team.getEmployees().iterator(); iter.hasNext();) {
            iter.next();
            iter.remove();
        }
    }

    private Set<Resource> getResourcesByIds(Set<Long> ids) {
        return ids.stream()
                .distinct()
                .map(resourceRepository::findByResourceId)
                .collect(Collectors.toSet());
    }

    private void resetTeamResources(Team team) {
        for (Iterator<Resource> iter = team.getResources().iterator(); iter.hasNext();) {
            iter.next();
            iter.remove();
        }
    }
}
