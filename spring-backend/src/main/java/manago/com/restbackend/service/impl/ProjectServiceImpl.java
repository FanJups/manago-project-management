package manago.com.restbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.exception.model.ErrorMessage;
import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Customer;
import manago.com.restbackend.model.Project;
import manago.com.restbackend.model.Task;
import manago.com.restbackend.model.Team;
import manago.com.restbackend.repository.CustomerRepository;
import manago.com.restbackend.repository.ProjectRepository;
import manago.com.restbackend.repository.TaskRepository;
import manago.com.restbackend.repository.TeamRepository;
import manago.com.restbackend.service.ProjectService;
import manago.com.restbackend.shared.request.ProjectRequest;
import manago.com.restbackend.shared.response.ProjectResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private CustomerRepository customerRepository;
    private TeamRepository teamRepository;
    private TaskRepository taskRepository;
    private ManagoMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, CustomerRepository customerRepository, TeamRepository teamRepository, TaskRepository taskRepository, ManagoMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<ProjectResponse> all() {
        return projectRepository.findAll().stream()
                .map(mapper::projectToProjectResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse one(String name) {
        return Stream.of(projectRepository.findByName(name))
                .map(mapper::projectToProjectResponse)
                .findAny()
                .get();
    }

    @Override
    public ProjectResponse update(String name, ProjectRequest request) {
        Project project = projectRepository.findByName(name);
        project.setDescription(request.getDescription());
        project.setTeam(getTeamByName(request.getTeamName()));
        resetProjectCustomers(project);
        getCustomersByIds(request.getCustomerIds()).forEach(project::addCustomer);
        projectRepository.save(project);
        return mapper.projectToProjectResponse(project);
    }

    @Override
    public ProjectResponse create(ProjectRequest request) {
        Project project = mapper.projectRequestToProject(request);
        getCustomersByIds(request.getCustomerIds()).forEach(project::addCustomer);
        Stream.of(getTeamByName(request.getTeamName())).forEach(project::setTeam);
        projectRepository.save(project);
        return mapper.projectToProjectResponse(project);
    }

    @Override
    @Transactional
    public void delete(String name) {
        Project project = projectRepository.findByName(name);
        if(project == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());

        project.setCustomers(null);
        project.setTeam(null);

        try {
            List<Long> taskIds = taskRepository
                    .findAllByProject(project)
                    .stream()
                    .map(t -> t.getTaskId())
                    .collect(Collectors.toList());

            try {
                for (Iterator<Long> iter = taskIds.iterator(); iter.hasNext();){
                    Long id = iter.next();
                    if(taskRepository.findByTaskId(id).getParent() != null) {
                        taskRepository.deleteByTaskId(id);
                        iter.remove();
                    }
                }
                for (Iterator<Long> iter = taskIds.iterator(); iter.hasNext();){
                    Long id = iter.next();
                    taskRepository.deleteByTaskId(id);
                    iter.remove();
                }
            } catch (Exception e) {
                throw new RuntimeException(ErrorMessages.STATUS_NOT_FOUND.getErrorMessage());
            }

        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.STATUS_IN_USE.getErrorMessage());
        }

        projectRepository.save(project);

        try {
            projectRepository.deleteByName(name);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.RECORD_NOT_DELETED.getErrorMessage());
        }
    }

    private Set<Customer> getCustomersByIds(Set<Long> ids) {
        return ids.stream()
                .distinct()
                .map(customerRepository::findByCustomerId)
                .collect(Collectors.toSet());
    }

    private Team getTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    private void resetProjectCustomers(Project project) {
        for (Iterator<Customer> iter = project.getCustomers().iterator(); iter.hasNext();) {
            iter.next();
            iter.remove();
        }
    }

}
