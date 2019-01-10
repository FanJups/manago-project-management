package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.TeamRequest;
import manago.com.restbackend.shared.response.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> all();
    TeamResponse one(String id);
    TeamResponse update(String id, TeamRequest request);
    TeamResponse create(TeamRequest request);
    void delete(String id);
}
