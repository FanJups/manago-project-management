package manago.com.restbackend.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponse {
    private String name;
    private String description;
    private Set<CustomerResponse> customerResponses;
    private TeamResponse teamResponse;
}
