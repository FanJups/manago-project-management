package manago.com.restbackend.repository;

import manago.com.restbackend.model.Project;
import manago.com.restbackend.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, String> {
    @Override
    List<Project> findAll();
    Project findByName(String name);
    void deleteByName(String name);
    List<Project> findAllByTeam(Team team);
}
