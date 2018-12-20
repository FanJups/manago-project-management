package manago.com.restbackend.util;

import manago.com.restbackend.model.Customer;
import manago.com.restbackend.model.Project;
import manago.com.restbackend.model.Task;
import manago.com.restbackend.model.Team;
import manago.com.restbackend.shared.response.CustomerResponse;
import manago.com.restbackend.shared.response.ProjectResponse;
import manago.com.restbackend.shared.response.TaskResponse;
import manago.com.restbackend.shared.response.TeamResponse;
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
}
