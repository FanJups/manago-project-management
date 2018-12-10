package manago.com.restbackend.model;

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
    @ManyToMany(mappedBy = "employees")
    private Set<Team> teams = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST,
        mappedBy = "employee")
    private User user;

}
