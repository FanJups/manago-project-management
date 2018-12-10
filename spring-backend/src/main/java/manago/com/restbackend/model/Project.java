package manago.com.restbackend.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 5313493413859894402L;

    @Id
    private String name;

    @NotNull
    private String description;

    @ToString.Exclude
    @ManyToMany(mappedBy = "projects")
    private Set<Customer> customers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

}
