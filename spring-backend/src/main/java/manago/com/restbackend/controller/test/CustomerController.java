package manago.com.restbackend.controller.test;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.model.Customer;
import manago.com.restbackend.service.CustomerService;
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
    CustomerService customerService;

    @GetMapping
    @RequestMapping(path = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getAllCustomers() {
        return customerService.all();
    }

}
