package manago.com.restbackend.controller;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.service.impl.UserServiceImpl;
import manago.com.restbackend.shared.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
}
