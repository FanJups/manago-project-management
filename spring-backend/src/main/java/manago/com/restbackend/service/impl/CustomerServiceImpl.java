package manago.com.restbackend.service.impl;

import manago.com.restbackend.model.Customer;
import manago.com.restbackend.repository.CustomerRepository;
import manago.com.restbackend.service.CustomerService;
import manago.com.restbackend.shared.response.CustomerResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ManagoMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ManagoMapper mapper) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponse> all() {
        return customerRepository
                .findAll()
                .stream()
                .map(mapper::customerToCustomerResponse)
                .collect(Collectors.toList());
    }
}
