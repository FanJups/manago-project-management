package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> all();
}
