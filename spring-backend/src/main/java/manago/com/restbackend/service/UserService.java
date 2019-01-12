package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.UserRequest;
import manago.com.restbackend.shared.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> all();
    UserResponse one(String username);
    UserResponse update(String username, UserRequest request);
    UserResponse create(UserRequest request);
    void delete(String username);
}
