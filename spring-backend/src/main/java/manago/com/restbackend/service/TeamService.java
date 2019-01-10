package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.TeamRequest;
import manago.com.restbackend.shared.response.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> all();
    TeamResponse one(String id);
    TeamResponse updateTeam(String id, TeamRequest request);
    TeamResponse createTeam(TeamRequest request);
    void deleteTeam(String id);
}
