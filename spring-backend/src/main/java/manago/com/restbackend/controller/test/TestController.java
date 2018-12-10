package manago.com.restbackend.controller.test;

import manago.com.restbackend.model.Customer;
import manago.com.restbackend.model.Project;
import manago.com.restbackend.repository.CustomerRepository;
import manago.com.restbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProjectRepository projectRepository;

    @PostMapping(path = "/projects")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping(path = "/projects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }
}
