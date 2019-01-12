package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.StatusServiceImpl;
import manago.com.restbackend.shared.request.ProjectRequest;
import manago.com.restbackend.shared.request.StatusRequest;
import manago.com.restbackend.shared.response.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Slf4j
@CrossOrigin
public class StatusController {

    @Autowired
    StatusServiceImpl statusService;

    @RequestMapping(method = GET, path = "/statuses", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StatusResponse> getAllStatuses() {
        log.info("GET /statuses");
        return statusService.all();
    }

    @RequestMapping(method = POST, path = "/statuses", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public StatusResponse addStatus(@RequestBody StatusRequest request) {
        log.info("POST /statuses");
        return statusService.create(request);
    }

    @RequestMapping(method = DELETE, path = "/statuses/{name}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStatus(@PathVariable String name) {
        log.info("DELETE /statuses/" + name);
        statusService.delete(name);
    }
}
