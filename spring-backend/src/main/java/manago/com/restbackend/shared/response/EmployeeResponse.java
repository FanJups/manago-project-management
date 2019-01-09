package manago.com.restbackend.shared.response;

import lombok.Data;

@Data
public class EmployeeResponse {
    private long employeeId;
    private String firstName;
    private String lastName;
    private Double salary;
    private String employmentType;
}
