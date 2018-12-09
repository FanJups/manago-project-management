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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task implements Serializable {
    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    private Long taskId;

    @NotNull
    private String name;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "parent_id")
    private Task parent;

    @OneToMany(mappedBy = "parent")
    private Set <Task> subtasks = new HashSet<Task>();

}
