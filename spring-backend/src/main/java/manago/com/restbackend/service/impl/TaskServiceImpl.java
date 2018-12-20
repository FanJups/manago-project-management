package manago.com.restbackend.service.impl;

import manago.com.restbackend.repository.TaskRepository;
import manago.com.restbackend.service.TaskService;
import manago.com.restbackend.shared.response.TaskResponse;
import manago.com.restbackend.shared.response.TeamResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ManagoMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, ManagoMapper managoMapper) {
        this.taskRepository = taskRepository;
        this.mapper = managoMapper;
    }

    @Override
    public List<TaskResponse> all() {
        return taskRepository
                .findAll()
                .stream()
                .map(mapper::taskToTaskResponse)
                .collect(Collectors.toList());
    }
}
