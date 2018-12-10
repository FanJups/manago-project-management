package manago.com.restbackend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team implements Serializable {
    private static final long serialVersionUID = 5313493413859894406L;

    @Id
    private String name;

    private Long size;

    private Double monthlyCost;

    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "team_employee",
            joinColumns = { @JoinColumn(name = "name")},
            inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    Set<Employee> employees = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "team_resource",
            joinColumns = { @JoinColumn(name = "name")},
            inverseJoinColumns = { @JoinColumn(name = "resource_id")}
    )
    Set<Resource> resources = new HashSet<>();

}
