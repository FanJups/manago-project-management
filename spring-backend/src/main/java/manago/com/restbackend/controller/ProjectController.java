package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.ProjectServiceImpl;
import manago.com.restbackend.shared.request.ProjectRequest;
import manago.com.restbackend.shared.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectServiceImpl projectService;

    @GetMapping
    @RequestMapping(path = "/projects", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ProjectResponse> getAllProjects() {
        return projectService.all();
    }

    @GetMapping
    @RequestMapping(path = "/projects/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectResponse getProject(@PathVariable String name) {
        return projectService.one(name);
    }

    @PutMapping
    @RequestMapping(path = "/projects/{name}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectResponse updateProject(@PathVariable String name, @RequestBody ProjectRequest request) {
        return projectService.update(name, request);
    }

    @PostMapping
    @RequestMapping(path = "/projects", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectResponse createProject(@RequestBody ProjectRequest request) {
        return projectService.create(request);
    }

}
