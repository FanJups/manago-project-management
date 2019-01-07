package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> all();
    UserResponse one(String id);
}
