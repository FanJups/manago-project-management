package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> all();
    CustomerResponse one(Long id);
}
