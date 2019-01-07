package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.model.Task;
import manago.com.restbackend.service.impl.TaskServiceImpl;
import manago.com.restbackend.shared.request.TaskRequest;
import manago.com.restbackend.shared.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping(method = RequestMethod.GET, path = "/tasks", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TaskResponse> getAllTasks() {
        log.info("GET /tasks");
        return taskService.all();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tasks/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TaskResponse getTask(@PathVariable String id) {
        log.info("GET /tasks/" + id);
        return taskService.one(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/task/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TaskResponse updateTask(@PathVariable String id, @RequestBody TaskRequest request) {
        log.info("PUT /tasks/" + id);
        return taskService.update(Long.parseLong(id), request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/task", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        log.info("POST /task");
        return taskService.create(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/task/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        log.info("DELETE /task" + id);
        taskService.delete(Long.parseLong(id));
    }

}
