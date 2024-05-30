package springbootlearning.service;

import springbootlearning.dto.SubscriptionTypeDto;
import springbootlearning.model.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> findAll();

    SubscriptionType findById(Long id);

    SubscriptionType create(SubscriptionTypeDto subscriptionTypeDto);

    SubscriptionType update(Long id, SubscriptionType subscriptionType);

    void delete(Long id);
}
