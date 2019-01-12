package manago.com.restbackend.service.impl;

import manago.com.restbackend.exception.model.ErrorMessage;
import manago.com.restbackend.exception.model.ErrorMessages;
import manago.com.restbackend.model.Resource;
import manago.com.restbackend.model.ResourceType;
import manago.com.restbackend.repository.ResourceRepository;
import manago.com.restbackend.repository.ResourceTypeRepository;
import manago.com.restbackend.repository.TeamRepository;
import manago.com.restbackend.service.ResourceService;
import manago.com.restbackend.shared.request.ResourceRequest;
import manago.com.restbackend.shared.request.TeamRequest;
import manago.com.restbackend.shared.response.ResourceResponse;
import manago.com.restbackend.shared.response.ResourceTypeResponse;
import manago.com.restbackend.util.ManagoMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceRepository resourceRepository;
    private ResourceTypeRepository resourceTypeRepository;
    private TeamRepository teamRepository;
    private ManagoMapper mapper;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceTypeRepository resourceTypeRepository, TeamRepository teamRepository, ManagoMapper mapper) {
        this.resourceRepository = resourceRepository;
        this.resourceTypeRepository = resourceTypeRepository;
        this.teamRepository = teamRepository;
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

    private void checkMissingFields(ResourceRequest request) {
        if(request.getName() == null
                || request.getCost() == null
                || request.getManufacturer() == null
                || request.getBoughtAt() == null
                || request.getResourceTypeRequest() == null)
            throw new RuntimeException(ErrorMessages.MISSING_FIELD.getErrorMessage());
    }

    private Resource addResourceType(Resource resource, ResourceRequest request) {
        ResourceType resourceType = resourceTypeRepository.findByName(request.getResourceTypeRequest().getName());
        if(resourceType == null)
            throw new RuntimeException(ErrorMessages.RESOURCE_TYPE_NOT_FOUND.getErrorMessage());
        resource.setResourceType(resourceType);
        return resource;
    }

    public ResourceResponse update(Long id, ResourceRequest request) {
        Resource resource = resourceRepository.findByResourceId(id);
        if(resource == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());

        checkMissingFields(request);
        resource.setName(request.getName());
        resource.setCost(request.getCost());
        resource.setManufacturer(request.getManufacturer());
        resource.setBoughtAt(request.getBoughtAt());

        resource = addResourceType(resource, request);

        resourceRepository.save(resource);

        return mapper.resourceToResourceResponse(resource);
    }

    public ResourceResponse create(ResourceRequest request) {
        Resource resource = mapper.resourceRequestToResource(request);
        checkMissingFields(request);

        resource = addResourceType(resource, request);

        resourceRepository.save(resource);

        return mapper.resourceToResourceResponse(resource);
    }

    public void delete(Long id) {
        Resource resource = resourceRepository.findByResourceId(id);
        if(resource == null)
            throw new RuntimeException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        teamRepository.findAll()
                .forEach(team -> team.deleteResource(resource));
        try {
            resourceRepository.delete(resource);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.RECORD_NOT_DELETED.getErrorMessage());
        }
    }
}
