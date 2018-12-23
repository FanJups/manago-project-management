package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 5313493413859894401L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    private String company;

    private String address;

    private String city;

    private String zipCode;


    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
            mappedBy = "customers")
    Set<Project> projects = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(company, customer.company) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(zipCode, customer.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, email, company, address, city, zipCode);
    }

}
