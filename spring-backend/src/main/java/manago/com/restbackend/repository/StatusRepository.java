package manago.com.restbackend.repository;

import manago.com.restbackend.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, String> {
    @Override
    List<Status> findAll();
    Status findByName(String name);
}
