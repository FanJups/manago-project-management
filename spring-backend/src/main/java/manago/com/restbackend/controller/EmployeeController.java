package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.EmployeeServiceImpl;
import manago.com.restbackend.shared.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @RequestMapping(method = RequestMethod.GET, path = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<EmployeeResponse> getAllEmployees() {
        log.info("GET /employees");
        return employeeService.all();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/employees/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public EmployeeResponse one(@PathVariable String id) {
        log.info("GET /employees/" + id);
        return employeeService.one(Long.parseLong(id));
    }
}
