package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
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
    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "customer_project",
            joinColumns = { @JoinColumn(name = "customer_id")},
            inverseJoinColumns = { @JoinColumn(name = "name")}
    )
    Set<Project> projects = new HashSet<>();

}
