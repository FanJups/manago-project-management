package manago.com.restbackend.service.impl;

import manago.com.restbackend.repository.ProjectRepository;
import manago.com.restbackend.service.ProjectService;
import manago.com.restbackend.shared.response.ProjectResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ManagoMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ManagoMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectResponse> all() {
        return projectRepository.findAll().stream()
                .map(mapper::projectToProjectResponse)
                .collect(Collectors.toList());
    }

}
