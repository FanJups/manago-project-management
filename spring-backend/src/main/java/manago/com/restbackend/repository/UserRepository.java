package manago.com.restbackend.repository;

import manago.com.restbackend.model.Employee;
import manago.com.restbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    @Override
    List<User> findAll();
    User findByUsername(String username);
    List<User> findAllByEmployee(Employee employee);
}
