package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.ProjectServiceImpl;
import manago.com.restbackend.shared.response.ProjectResponse;
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
public class ProjectController {

    @Autowired
    ProjectServiceImpl projectService;

    @GetMapping
    @RequestMapping(path = "/projects", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ProjectResponse> getAllCustomers() {
        return projectService.all();
    }

}
