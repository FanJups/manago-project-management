package manago.com.restbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
@Table(name = "project")
public class Project extends Auditor implements Serializable {
    private static final long serialVersionUID = 5313493413859894402L;

    @Id
    private String name;

    @NotNull
    private String description;

    @ToString.Exclude
    @ManyToMany(mappedBy = "projects")
    private Set<Customer> customers = new HashSet<>();

}
