package manago.com.restbackend.shared.request;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectRequest {
    private String name;
    private String description;
    private Set<Long> customerIds;
    private String teamName;
}
