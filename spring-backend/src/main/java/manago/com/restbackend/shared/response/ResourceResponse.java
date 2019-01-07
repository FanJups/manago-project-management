package manago.com.restbackend.shared.response;

import lombok.Data;
import manago.com.restbackend.model.ResourceType;

import java.util.Date;

@Data
public class ResourceResponse {
    private Long resourceId;
    private String name;
    private Double cost;
    private String manufacturer;
    private Date boughtAt;
    private ResourceType resourceType;
}
