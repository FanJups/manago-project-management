package manago.com.restbackend.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Status implements Serializable {
    private static final long serialVersionUID = 5313493413859894404L;
}
