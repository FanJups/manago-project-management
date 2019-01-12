package manago.com.restbackend.shared.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private Long employeeId;
}
