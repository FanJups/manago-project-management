package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.CustomerServiceImpl;
import manago.com.restbackend.shared.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping
    @RequestMapping(path = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CustomerResponse> getAllCustomers() {
        return customerService.all();
    }

    @GetMapping
    @RequestMapping(path = "/customers/:id", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerResponse getCustomer(@PathVariable String id) {
        return customerService.one(Long.parseLong(id));
    }


}
