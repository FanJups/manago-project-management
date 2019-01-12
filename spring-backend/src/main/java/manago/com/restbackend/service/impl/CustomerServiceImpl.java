package manago.com.restbackend.service.impl;

import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Customer;
import manago.com.restbackend.repository.CustomerRepository;
import manago.com.restbackend.service.CustomerService;
import manago.com.restbackend.shared.request.CustomerRequest;
import manago.com.restbackend.shared.response.CustomerResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public CustomerResponse one(Long id) {
        return mapper.customerToCustomerResponse(customerRepository.findByCustomerId(id));
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findByCustomerId(id);

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setCompany(request.getCompany());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setZipCode(request.getZipCode());

        customerRepository.save(customer);

        return mapper.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = mapper.customerRequestToCustomer(request);
        customerRepository.save(customer);
        return mapper.customerToCustomerResponse(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerRepository.deleteByCustomerId(id);
    }
}
