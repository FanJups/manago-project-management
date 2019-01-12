package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.HistoryResponse;

import java.util.List;

public interface HistoryService {
    List<HistoryResponse> taskHistory(Long taskId);
}
