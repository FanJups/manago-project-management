package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.StatusServiceImpl;
import manago.com.restbackend.shared.response.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("GET /teams");
        return statusService.all();
    }
}
