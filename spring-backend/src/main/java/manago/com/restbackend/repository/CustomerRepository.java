package manago.com.restbackend.repository;

import manago.com.restbackend.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    Customer findByCustomerId(Long customerId);
    void deleteByCustomerId(Long customerId);

    @Query(value = "SELECT getcustomer(?1)", nativeQuery = true)
    String getCustData(Long customerId);
}
