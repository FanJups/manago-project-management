package manago.com.restbackend.service.impl;

import manago.com.restbackend.model.Resource;
import manago.com.restbackend.repository.ResourceRepository;
import manago.com.restbackend.repository.ResourceTypeRepository;
import manago.com.restbackend.service.ResourceService;
import manago.com.restbackend.shared.response.ResourceResponse;
import manago.com.restbackend.shared.response.ResourceTypeResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceRepository resourceRepository;
    private ResourceTypeRepository resourceTypeRepository;
    private ManagoMapper mapper;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceTypeRepository resourceTypeRepository, ManagoMapper mapper) {
        this.resourceRepository = resourceRepository;
        this.resourceTypeRepository = resourceTypeRepository;
        this.mapper = mapper;
    }

    public List<ResourceResponse> all() {
        return resourceRepository
                .findAll()
                .stream()
                .map(mapper::resourceToResourceResponse)
                .collect(Collectors.toList());
    }

    public ResourceResponse one(Long id) {
        return mapper.resourceToResourceResponse(resourceRepository.findByResourceId(id));
    }

    public List<ResourceTypeResponse> types() {
        return resourceTypeRepository
                .findAll()
                .stream()
                .map(mapper::resourceTypeToResourceTypeResponse)
                .collect(Collectors.toList());
    }
}
