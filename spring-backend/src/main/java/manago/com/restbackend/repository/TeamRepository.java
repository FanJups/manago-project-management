package manago.com.restbackend.repository;


import manago.com.restbackend.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, String> {
    @Override
    List<Team> findAll();
}
