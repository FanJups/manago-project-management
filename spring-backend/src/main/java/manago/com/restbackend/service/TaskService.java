package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.TaskRequest;
import manago.com.restbackend.shared.response.TaskResponse;
import manago.com.restbackend.shared.response.TeamResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> all();
    TaskResponse one(long id);
    TaskResponse update(long id, TaskRequest request);
    TaskResponse create(TaskRequest request);
    void delete(long id);
}
