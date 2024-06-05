package springbootlearning.service.impl;

import org.springframework.stereotype.Service;
import springbootlearning.dto.PaymentProcessDto;
import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.exception.BusinessException;
import springbootlearning.exception.NotFoundException;
import springbootlearning.integration.WsRaspayIntegration;
import springbootlearning.mapper.UserPaymentInfoMapper;
import springbootlearning.mapper.wsraspay.CustomerMapper;
import springbootlearning.model.User;
import springbootlearning.model.UserPaymentInfo;
import springbootlearning.repository.UserPaymentInfoRepository;
import springbootlearning.repository.UserRepository;
import springbootlearning.service.PaymentInfoService;

import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;

    PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository,
                           WsRaspayIntegration wsRaspayIntegration) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
    }

    @Override
    public Boolean process(PaymentProcessDto paymentProcessDto) {

        var userOpt = userRepository.findById(paymentProcessDto.getUserPaymentInfoDto().getUserId());
        if (userOpt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = userOpt.get();
        if (Objects.nonNull(user.getSubscriptionType())) {
            throw new BusinessException("Pagamento não pode ser processado, pois usuario ja possui assinatura");
        }

        // creates and updates the user in the integration
        CustomerDto customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));




        UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(paymentProcessDto.getUserPaymentInfoDto(), user);
        userPaymentInfoRepository.save(userPaymentInfo);
        return null;
    }
}
