package manago.com.restbackend.service.impl;

import manago.com.restbackend.repository.TeamRepository;
import manago.com.restbackend.service.TeamService;
import manago.com.restbackend.shared.response.TeamResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private ManagoMapper mapper;

    public TeamServiceImpl (TeamRepository teamRepository, ManagoMapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TeamResponse> all() {
        return teamRepository
                .findAll()
                .stream()
                .map(mapper::teamToTeamResponse)
                .collect(Collectors.toList());
    }
}
