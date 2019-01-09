package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.ProjectServiceImpl;
import manago.com.restbackend.shared.request.ProjectRequest;
import manago.com.restbackend.shared.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectServiceImpl projectService;

    @RequestMapping(method = RequestMethod.GET, path = "/projects", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ProjectResponse> getAllProjects() {
        log.info("GET /projects");
        return projectService.all();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/projects/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectResponse getProject(@PathVariable String name) {
        log.info("GET /projects/" + name);
        return projectService.one(name);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/projects/{name}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectResponse updateProject(@PathVariable String name, @RequestBody ProjectRequest request) {
        log.info("PUT /projects/name");
        return projectService.update(name, request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/projects", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectResponse createProject(@RequestBody ProjectRequest request) {
        log.info("POST /projects");
        return projectService.create(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/projects/{name}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String name) {
        log.info("DELETE /projects/" + name);
        projectService.delete(name);
    }
}
