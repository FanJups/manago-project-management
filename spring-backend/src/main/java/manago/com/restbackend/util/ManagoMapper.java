package manago.com.restbackend.util;

import manago.com.restbackend.model.*;
import manago.com.restbackend.shared.request.CustomerRequest;
import manago.com.restbackend.shared.request.ProjectRequest;
import manago.com.restbackend.shared.request.StatusRequest;
import manago.com.restbackend.shared.request.TaskRequest;
import manago.com.restbackend.shared.response.*;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ManagoMapper {

    private ModelMapper modelMapper;

    public ManagoMapper() {
        this.modelMapper = new ModelMapper();
    }

    public CustomerResponse customerToCustomerResponse(Customer customer) {
        return modelMapper.map(customer, CustomerResponse.class);
    }

    public ProjectResponse projectToProjectResponse(Project project) {
        ProjectResponse resp = modelMapper.map(project, ProjectResponse.class);
        resp.setCustomerResponses(
                project.getCustomers().stream()
                        .map(this::customerToCustomerResponse)
                        .collect(Collectors.toSet())
        );
        resp.setTeamResponse(
                this.teamToTeamResponse(project.getTeam())
        );
        return resp;
    }

    public Project projectRequestToProject(ProjectRequest request) {
        return modelMapper.map(request, Project.class);
    }

    public TeamResponse teamToTeamResponse(Team team) {
        return modelMapper.map(team, TeamResponse.class);
    }

    public TaskResponse taskToTaskResponse(Task task) {
        TaskResponse resp = modelMapper.map(task, TaskResponse.class);
        if (task.getParent() != null) {
            resp.setParentId(task.getParent().getTaskId());
        }
        if (task.getSubtasks().size() > 0) {
            resp.setSubTaskResponses(
                    task.getSubtasks().stream()
                        .map(this::taskToTaskResponse)
                        .collect(Collectors.toSet())
            );
        }
        return resp;
    }

    public Task taskRequestToTask(TaskRequest request) {
        return modelMapper.map(request, Task.class);
    }

    public Customer customerRequestToCustomer(CustomerRequest request) {
        return modelMapper.map(request, Customer.class);
    }

    public Status statusRequestToStatus(StatusRequest request) {
        return modelMapper.map(request, Status.class);
    }

    public EmployeeResponse employeeToEmployeeResponse(Employee employee) { return modelMapper.map(employee, EmployeeResponse.class); }

    public UserResponse userToUserResponse(User user) { return modelMapper.map(user, UserResponse.class); }

    public ResourceResponse resourceToResourceResponse(Resource resource) { return modelMapper.map(resource, ResourceResponse.class); }

    public ResourceTypeResponse resourceTypeToResourceTypeResponse(ResourceType resourceType) {
        return modelMapper.map(resourceType, ResourceTypeResponse.class);
    }
}
