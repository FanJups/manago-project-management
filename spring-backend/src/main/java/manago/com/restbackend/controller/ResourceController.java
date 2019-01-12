package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.ResourceServiceImpl;
import manago.com.restbackend.shared.request.ResourceRequest;
import manago.com.restbackend.shared.response.ResourceResponse;
import manago.com.restbackend.shared.response.ResourceTypeResponse;
import manago.com.restbackend.shared.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class ResourceController {

    @Autowired
    ResourceServiceImpl resourceService;

    @RequestMapping(method = RequestMethod.GET, path = "/resources", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ResourceResponse> getAllUsers() {
        log.info("GET /resources");
        return resourceService.all();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/resources/types", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ResourceTypeResponse> types() {
        log.info("GET /resources/types");
        return resourceService.types();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/resources/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResourceResponse one(@PathVariable String id) {
        log.info("GET /resources/" + id);
        return resourceService.one(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/resources/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResourceResponse update(@PathVariable String id, @RequestBody ResourceRequest request) {
        log.info("PUT /resources/" + id);
        return resourceService.update(Long.parseLong(id), request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/resources", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResourceResponse create(@RequestBody ResourceRequest request) {
        log.info("POST /resources");
        return resourceService.create(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/resources/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.info("DELETE /resources/" + id);
        resourceService.delete(Long.parseLong(id));
    }
}
