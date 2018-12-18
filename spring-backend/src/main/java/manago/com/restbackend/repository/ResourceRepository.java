package manago.com.restbackend.repository;

import manago.com.restbackend.model.Resource;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
    @Override
    List<Resource> findAll();
}
