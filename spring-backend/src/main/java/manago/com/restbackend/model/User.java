package manago.com.restbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 5313493413859894410L;

    @Id
    private String username;

    @NotNull
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;
}
