package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.TeamResponse;

import java.util.List;

public interface TaskService {
    List<TeamResponse> all();
}
