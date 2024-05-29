package springbootlearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootlearning.model.SubscriptionType;
import springbootlearning.repository.SubscriptionTypeRepository;
import springbootlearning.service.SubscriptionTypeService;

import java.util.List;
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

        Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findById(id);

        if (optionalSubscriptionType.isPresent()) {
            return optionalSubscriptionType.get();
        }

        return null;
    }

    @Override
    public SubscriptionType create(SubscriptionType subscriptionType) {
        return null;
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionType subscriptionType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
