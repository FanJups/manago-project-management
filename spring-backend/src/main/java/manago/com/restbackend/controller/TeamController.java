package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.SQLProcedureService;
import manago.com.restbackend.service.impl.TeamServiceImpl;
import manago.com.restbackend.shared.request.TeamRequest;
import manago.com.restbackend.shared.response.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Slf4j
@CrossOrigin
public class TeamController {

    @Autowired
    TeamServiceImpl teamService;

    @Autowired
    SQLProcedureService sqlProcedureService;

    @RequestMapping(method = GET, path = "/teams", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TeamResponse> getAllTeams() {
        log.info("GET /teams");
        return teamService.all();
    }

    @RequestMapping(method = GET, path = "/teams/procedure/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateAllTeams(@PathVariable String name) {
        log.info("GET /procedure");
        sqlProcedureService.updateTeam(name);
    }

    @RequestMapping(method = GET, path = "/teams/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TeamResponse one(@PathVariable String name) {
        log.info("GET /teams/" + name);
        return teamService.one(name);
    }

    @RequestMapping(method = PUT, path = "/teams/{name}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TeamResponse updateTeam(@PathVariable String name, @RequestBody TeamRequest request) {
        log.info("PUT /teams/" + name);
        return teamService.update(name, request);
    }

    @RequestMapping(method = POST, path = "/teams", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TeamResponse createTeam(@RequestBody TeamRequest request) {
        log.info("POST /teams");
        return teamService.create(request);
    }

    @RequestMapping(method = DELETE, path = "/teams/{name}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable String name) {
        log.info("DELETE /teams/" + name);
        teamService.delete(name);
    }

}
