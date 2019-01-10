package manago.com.restbackend.shared.request;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceRequest {

    private Long resourceId;
    private String name;
    private Double cost;
    private String manufacturer;
    private Date boughtAt;
    private ResourceTypeRequest resourceTypeRequest;
}
