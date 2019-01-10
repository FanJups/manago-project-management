package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "team")
public class Team implements Serializable {
    private static final long serialVersionUID = 5313493413859894406L;

    @Id
    private String name;

    private Long size;

    private Double monthlyCost;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    @JsonManagedReference
    @JoinTable(
            name = "team_employee",
            joinColumns = { @JoinColumn(name = "name")},
            inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    private Set<Employee> employees = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "team_resource",
            joinColumns = { @JoinColumn(name = "name")},
            inverseJoinColumns = { @JoinColumn(name = "resource_id")}
    )
    @ToString.Exclude
    @JsonManagedReference
    private Set<Resource> resources = new HashSet<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void deleteEmployee(Employee employee) {
        employees.remove(employee);
    }

}
