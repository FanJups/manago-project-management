package manago.com.restbackend.service.impl;

import manago.com.restbackend.model.Task;
import manago.com.restbackend.repository.ProjectRepository;
import manago.com.restbackend.repository.StatusRepository;
import manago.com.restbackend.repository.TaskRepository;
import manago.com.restbackend.service.TaskService;
import manago.com.restbackend.shared.request.TaskRequest;
import manago.com.restbackend.shared.response.TaskResponse;
import manago.com.restbackend.shared.response.TeamResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private StatusRepository statusRepository;
    private ManagoMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, StatusRepository statusRepository, ManagoMapper managoMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.mapper = managoMapper;
    }

    @Override
    public List<TaskResponse> all(String projectName) {
        return taskRepository
                .findAll()
                .stream()
                .filter(task -> task.getProject().getName().equals(projectName))
                .filter(task -> task.getParent() == null)
                .map(mapper::taskToTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse one(long id) {
        return mapper.taskToTaskResponse(taskRepository.findByTaskId(id));
    }

    @Override
    public TaskResponse update(long id, TaskRequest request) {
        Task task = taskRepository.findByTaskId(id);
        task.setName(request.getName());
        task.setParent(taskRepository.findByTaskId(request.getParentId()));
        task.setStatus(mapper.statusRequestToStatus(request.getStatusRequest()));
        taskRepository.save(task);
        return mapper.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse create(TaskRequest request, String projectName) {
        Task task = mapper.taskRequestToTask(request);
        task.setProject(projectRepository.findByName(projectName));
        taskRepository.save(task);
        return mapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public void delete(long id) {
        taskRepository.deleteByTaskId(id);
    }
}
