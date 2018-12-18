package manago.com.restbackend.repository;

import manago.com.restbackend.model.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Long> {
    @Override
    List<History> findAll();
}
