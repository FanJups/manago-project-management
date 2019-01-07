package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.TeamServiceImpl;
import manago.com.restbackend.shared.response.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class TeamController {

    @Autowired
    TeamServiceImpl teamService;

    @GetMapping
    @RequestMapping(path = "/teams", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TeamResponse> getAllTeams() {
        return teamService.all();
    }

}
