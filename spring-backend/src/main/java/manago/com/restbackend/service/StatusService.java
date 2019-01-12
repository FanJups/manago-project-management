package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.StatusResponse;

import java.util.List;

public interface StatusService {
    List<StatusResponse> all();
}
