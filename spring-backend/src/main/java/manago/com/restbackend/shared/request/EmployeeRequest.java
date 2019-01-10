package manago.com.restbackend.shared.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Double salary;
    private String employmentType;
}
