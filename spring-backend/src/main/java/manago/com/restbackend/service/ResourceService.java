package manago.com.restbackend.service;

import manago.com.restbackend.shared.request.ResourceRequest;
import manago.com.restbackend.shared.response.ResourceResponse;
import manago.com.restbackend.shared.response.ResourceTypeResponse;

import java.util.List;

public interface ResourceService {
    List<ResourceResponse> all();
    ResourceResponse one(Long id);
    List<ResourceTypeResponse> types();
    ResourceResponse update(Long id, ResourceRequest request);
    ResourceResponse create(ResourceRequest request);
    void delete(Long id);
}
