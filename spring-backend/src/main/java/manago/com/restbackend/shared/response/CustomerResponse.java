package manago.com.restbackend.shared.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerResponse {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String address;
    private String zipCode;
    private String city;
    private Set<ProjectResponse> projectResponses;
}
