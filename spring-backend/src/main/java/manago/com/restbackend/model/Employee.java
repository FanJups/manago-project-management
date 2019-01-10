package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 5313493413859894407L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Double salary;

    private String employmentType;

    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
            mappedBy = "employees")
    Set<Team> teams = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER,
        cascade = CascadeType.PERSIST,
        mappedBy = "employee")
    @JsonManagedReference
    private User user;

}
