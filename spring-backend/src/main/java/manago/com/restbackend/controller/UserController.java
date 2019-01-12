package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.UserServiceImpl;
import manago.com.restbackend.shared.request.UserRequest;
import manago.com.restbackend.shared.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET, path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getAllUsers() {
        log.info("GET /users");
        return userService.all();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse one(@PathVariable String username) {
        log.info("GET /users/" + username);
        return userService.one(username);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/users/{username}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse update(@PathVariable String username, @RequestBody UserRequest request) {
        log.info("PUT /users/" + username);
        return userService.update(username, request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse create(@RequestBody UserRequest request) {
        log.info("POST /users");
        return userService.create(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{username}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username) {
        log.info("DELETE /users/" + username);
        userService.delete(username);
    }
}
