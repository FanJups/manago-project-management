package manago.com.restbackend.shared.request;

import lombok.Data;

import java.util.Set;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequest {
    private Long taskId;
    private String name;
    private Long parentId;
    private Set<TaskRequest> subTaskRequests;
    private String statusName;
}
