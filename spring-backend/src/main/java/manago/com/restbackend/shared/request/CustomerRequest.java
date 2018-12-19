package manago.com.restbackend.shared.request;


import lombok.Data;

@Data
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String address;
    private String zipCode;
    private String city;
}
