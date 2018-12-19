package manago.com.restbackend.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponse {
    private String name;
    private Long size;
    private Double monthlyCost;
    private Set<EmployeeResponse> employeeResponses;
    private Set<ResourceResponse> resourceResponses;
}
