package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.ProjectRequest;
import manago.com.restbackend.shared.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> all();
    ProjectResponse one(String name);
    ProjectResponse update(String name, ProjectRequest request);
    ProjectResponse create(ProjectRequest request);
    void delete(String name);
}
