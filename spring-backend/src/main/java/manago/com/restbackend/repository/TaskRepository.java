package manago.com.restbackend.repository;

import manago.com.restbackend.model.Project;
import manago.com.restbackend.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();
    Task findByTaskId(long id);
    void deleteByTaskId(long id);
    List<Task> findAllByParent(Task parent);
    List<Task> findAllByProject(Project project);
}
