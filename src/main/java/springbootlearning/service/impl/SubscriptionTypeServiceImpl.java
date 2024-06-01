package springbootlearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import springbootlearning.controller.SubscriptionTypeController;
import springbootlearning.dto.SubscriptionTypeDto;
import springbootlearning.exception.BadRequestException;
import springbootlearning.exception.NotFoundException;
import springbootlearning.mapper.SubscriptionTypeMapper;
import springbootlearning.model.SubscriptionType;
import springbootlearning.repository.SubscriptionTypeRepository;
import springbootlearning.service.SubscriptionTypeService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private static final String UPDATE_SUBSCRIPTION_TYPE = "updateSubscriptionType";
    private static final String DELETE_SUBSCRIPTION_TYPE = "deleteSubscriptionType";


    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        return this.getSubscriptionType(id).add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).finfById(id)
                ).withSelfRel()
        ).add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).update(id, new SubscriptionTypeDto())
                ).withRel(UPDATE_SUBSCRIPTION_TYPE)
        ).add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).delete(id)
                ).withRel(DELETE_SUBSCRIPTION_TYPE)
        );
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) {
        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID must be null");
        }
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto dto) {
        this.getSubscriptionType(id);
        dto.setId(id);
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public void delete(Long id) {
        this.getSubscriptionType(id);
        subscriptionTypeRepository.deleteById(id);
    }

    private SubscriptionType getSubscriptionType(Long id) {
        Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findById(id);

        if (!optionalSubscriptionType.isPresent()) {
            throw new NotFoundException("Subscription type not found");
        }
        return optionalSubscriptionType.get();
    }
}
