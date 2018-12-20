package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.TaskResponse;
import manago.com.restbackend.shared.response.TeamResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> all();
}
