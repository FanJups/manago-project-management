package manago.com.restbackend.shared.request;

import lombok.Data;
import manago.com.restbackend.model.Employee;
import manago.com.restbackend.model.Resource;

import java.util.Set;

@Data
public class TeamRequest {
    private String name;
    private Long size;
    private Double monthlyCost;
    private Set<Long> employeeIds;
    private Set<Long> resourceIds;
}
