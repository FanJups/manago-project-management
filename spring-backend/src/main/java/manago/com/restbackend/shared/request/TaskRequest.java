package manago.com.restbackend.shared.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import manago.com.restbackend.model.Project;
import manago.com.restbackend.shared.request.StatusRequest;

import java.util.Set;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequest {
    private Long taskId;
    private String name;
    private Long parentId;
    private Set<TaskRequest> subTaskRequests;
    private StatusRequest statusRequest;
}
