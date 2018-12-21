package manago.com.restbackend.repository;

import manago.com.restbackend.model.ResourceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceTypeRepository extends CrudRepository<ResourceType, String> {
    @Override
    List<ResourceType> findAll();
}
