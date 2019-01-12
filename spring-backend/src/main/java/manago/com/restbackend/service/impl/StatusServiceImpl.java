package manago.com.restbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import manago.com.restbackend.repository.StatusRepository;
import manago.com.restbackend.service.StatusService;
import manago.com.restbackend.shared.response.StatusResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;
    private ManagoMapper mapper;

    public StatusServiceImpl(StatusRepository statusRepository, ManagoMapper mapper) {
        this.statusRepository = statusRepository;
        this.mapper = mapper;
    }

    public List<StatusResponse> all() {
        return statusRepository
                .findAll()
                .stream()
                .map(mapper::statusToStatusResponse)
                .collect(Collectors.toList());
    }
}
