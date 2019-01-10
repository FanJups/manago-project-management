package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.EmployeeRequest;
import manago.com.restbackend.shared.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> all();
    EmployeeResponse one(Long id);
    void delete(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    EmployeeResponse createEmployee(EmployeeRequest request);
}
