package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.HistoryService;
import manago.com.restbackend.service.impl.HistoryServiceImpl;
import manago.com.restbackend.shared.response.HistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class HistoryController {

    @Autowired
    HistoryServiceImpl historyService;

    @RequestMapping(method = RequestMethod.GET, path = "/history/{taskId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<HistoryResponse> taskHistory(@PathVariable String taskId) {
        log.info("GET /history/" + taskId);
        return historyService.taskHistory(Long.parseLong(taskId));
    }
}
