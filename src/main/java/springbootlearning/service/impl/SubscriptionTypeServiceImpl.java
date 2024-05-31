package springbootlearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootlearning.dto.SubscriptionTypeDto;
import springbootlearning.exception.BadRequestException;
import springbootlearning.exception.NotFoundException;
import springbootlearning.model.SubscriptionType;
import springbootlearning.repository.SubscriptionTypeRepository;
import springbootlearning.service.SubscriptionTypeService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        return this.getSubscriptionType(id);
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) {
        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID must be null");
        }
        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .accessMonth(dto.getAccessMonth())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto dto) {
        this.getSubscriptionType(id);

        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(id)
                .name(dto.getName())
                .accessMonth(dto.getAccessMonth())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build());

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
