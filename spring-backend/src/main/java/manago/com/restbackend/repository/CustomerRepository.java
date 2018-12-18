package manago.com.restbackend.repository;

import manago.com.restbackend.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();
}
