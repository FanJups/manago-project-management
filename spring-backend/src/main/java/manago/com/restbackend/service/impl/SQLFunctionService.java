package manago.com.restbackend.service.impl;

import manago.com.restbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQLFunctionService {

    @Autowired
    CustomerRepository customerRepository;

    public String getCustomerData(Long custId) {
        return customerRepository.getCustData(custId);
    }
}
