package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 5313493413859894402L;

    @Id
    private String name;

    @NotNull
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH
    })
    @ToString.Exclude
    @JsonManagedReference
    @JoinTable(
            name = "customer_project",
            joinColumns = { @JoinColumn(name = "name")},
            inverseJoinColumns = { @JoinColumn(name = "customer_id")}
    )
    private Set<Customer> customers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

}
