package manago.com.restbackend.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Status extends Auditor implements Serializable {
    private static final long serialVersionUID = 5313493413859894404L;
}
