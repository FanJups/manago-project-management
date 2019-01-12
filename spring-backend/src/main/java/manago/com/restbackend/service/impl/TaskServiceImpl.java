package manago.com.restbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Status;
import manago.com.restbackend.model.Task;
import manago.com.restbackend.repository.ProjectRepository;
import manago.com.restbackend.repository.StatusRepository;
import manago.com.restbackend.repository.TaskRepository;
import manago.com.restbackend.service.TaskService;
import manago.com.restbackend.shared.request.TaskRequest;
import manago.com.restbackend.shared.response.TaskResponse;
import manago.com.restbackend.shared.response.TeamResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private HistoryServiceImpl historyService;

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

    private Task taskSetter(Task task, TaskRequest request) {
        if(request.getParentId() == null) {
            task.setParent(null);
        } else {
            if(taskRepository.findByTaskId(request.getParentId()) != null)
                task.setParent(taskRepository.findByTaskId(request.getParentId()));
        }

        Status status = statusRepository.findByName(request.getStatusRequest().getName());
        if(status == null)
            throw new RuntimeException(ErrorMessages.STATUS_NOT_FOUND.getErrorMessage());
        task.setStatus(status);

        return task;
    }

    @Override
    public TaskResponse update(long id, TaskRequest request) {
        Task task = taskRepository.findByTaskId(id);
        task.setName(request.getName());
        task = taskSetter(task, request);
        taskRepository.save(task);
        historyService.addHistory(id, "Task Updated");
        return mapper.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse create(TaskRequest request, String projectName) {

        Task task = mapper.taskRequestToTask(request);
        task.setProject(projectRepository.findByName(projectName));
        task = taskSetter(task, request);
        taskRepository.save(task);
        task = mapper.taskRequestToTask(request);
        historyService.addHistory(task.getTaskId(), "Task Created");
        return mapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public void delete(long id) {
        taskRepository.findAllByParent(taskRepository.findByTaskId(id))
                .forEach(task -> taskRepository.deleteByTaskId(task.getTaskId()));
        historyService.addHistory(id, "Task Deleted");
        taskRepository.deleteByTaskId(id);
    }
}
