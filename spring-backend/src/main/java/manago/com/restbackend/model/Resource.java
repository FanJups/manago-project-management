package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Serializable {
    private static final long serialVersionUID = 5313493413859894408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @NotNull
    private String name;

    private Double cost;

    private String manufacturer;

    private Date boughtAt;

    @ToString.Exclude
    @ManyToMany(mappedBy = "resources")
    @JsonBackReference
    private Set<Team> teams = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_type")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResourceType resourceType;

}
