package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.TaskRequest;
import manago.com.restbackend.shared.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> all(String projectName);
    TaskResponse one(long id);
    TaskResponse update(long id, TaskRequest request);
    TaskResponse create(TaskRequest request, String projectName);
    void delete(long id);
}
