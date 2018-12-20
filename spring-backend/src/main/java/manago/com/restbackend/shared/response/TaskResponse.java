package manago.com.restbackend.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    private Long taskId;
    private String name;
    private Long parentId;
    private Set<TaskResponse> subTaskResponses;
    private StatusResponse statusResponse;
}
