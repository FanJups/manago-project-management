package manago.com.restbackend.service.impl;

import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.History;
import manago.com.restbackend.model.Task;
import manago.com.restbackend.repository.HistoryRepository;
import manago.com.restbackend.repository.TaskRepository;
import manago.com.restbackend.service.HistoryService;
import manago.com.restbackend.shared.response.HistoryResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepository historyRepository;
    private TaskRepository taskRepository;
    private ManagoMapper mapper;

    HistoryServiceImpl(HistoryRepository historyRepository, TaskRepository taskRepository, ManagoMapper mapper) {
        this.historyRepository = historyRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public List<HistoryResponse> taskHistory(Long taskId) {
        Task task = taskRepository.findByTaskId(taskId);
        if(task == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        List<History> history = historyRepository.findAllByTask(task);

        return history.stream()
                .map(mapper::historyToHistoryResponse)
                .collect(Collectors.toList());
    }

    public void addHistory(Long taskId, String message) {
        History history = new History();
        Task task = taskRepository.findByTaskId(taskId);
        history.setTask(task);
        history.setOperationId(message);

        historyRepository.save(history);
    }
}
