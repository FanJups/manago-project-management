package manago.com.restbackend.service;

import manago.com.restbackend.shared.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> all();
    EmployeeResponse one(Long id);
    void delete(Long id);
}
