package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> all();
}
