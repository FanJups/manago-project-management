package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.StatusRequest;
import manago.com.restbackend.shared.response.StatusResponse;

import java.util.List;

public interface StatusService {
    List<StatusResponse> all();
    StatusResponse create(StatusRequest request);
    void delete(String name);
}
