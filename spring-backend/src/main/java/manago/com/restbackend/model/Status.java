package manago.com.restbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status implements Serializable {
    private static final long serialVersionUID = 5313493413859894404L;

    @Id
    private String name;
}
