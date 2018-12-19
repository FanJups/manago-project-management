package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.CustomerServiceImpl;
import manago.com.restbackend.shared.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
