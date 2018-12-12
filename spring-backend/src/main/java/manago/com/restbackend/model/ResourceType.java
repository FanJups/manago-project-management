package manago.com.restbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResourceType implements Serializable {
    private static final long serialVersionUID = 5313493413859894409L;

    @Id
    private String name;
}
