package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.CustomerServiceImpl;
import manago.com.restbackend.shared.request.CustomerRequest;
import manago.com.restbackend.shared.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @RequestMapping(method = RequestMethod.GET, path = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CustomerResponse> getAllCustomers() {
        log.info("GET /customers");
        return customerService.all();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerResponse getCustomer(@PathVariable String id) {
        log.info("GET /customers/" + id);
        return customerService.one(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/customers/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerResponse updateCustomer(@PathVariable String id, @RequestBody CustomerRequest request) {
        log.info("PUT /customers/" + id);
        return customerService.update(Long.parseLong(id), request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerResponse createCustomer(@RequestBody CustomerRequest request) {
        log.info("POST /customers");
        return customerService.create(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/customers/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String id) {
        log.info("DELETE /customers/" + id);
        customerService.delete(Long.parseLong(id));
    }
}
