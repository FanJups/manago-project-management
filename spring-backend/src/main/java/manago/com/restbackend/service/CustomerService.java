package manago.com.restbackend.service;

import manago.com.restbackend.model.Customer;
import manago.com.restbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> all() {
        return this.customerRepository.findAll();
    }
}
