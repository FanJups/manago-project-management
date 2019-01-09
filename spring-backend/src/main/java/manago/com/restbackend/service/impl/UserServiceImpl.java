package manago.com.restbackend.service.impl;

import manago.com.restbackend.model.User;
import manago.com.restbackend.repository.UserRepository;
import manago.com.restbackend.service.UserService;
import manago.com.restbackend.shared.response.UserResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ManagoMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ManagoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserResponse> all() {
        return userRepository
                .findAll()
                .stream()
                .map(mapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse one(String id) {
        return mapper.userToUserResponse(userRepository.findByUsername(id));
    }
}
