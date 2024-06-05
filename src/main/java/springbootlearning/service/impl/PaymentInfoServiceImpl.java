package springbootlearning.service.impl;

import org.springframework.stereotype.Service;
import springbootlearning.dto.PaymentProcessDto;
import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.dto.wsraspay.OrderDto;
import springbootlearning.dto.wsraspay.PaymentDto;
import springbootlearning.exception.BusinessException;
import springbootlearning.exception.NotFoundException;
import springbootlearning.integration.MailIntegration;
import springbootlearning.integration.WsRaspayIntegration;
import springbootlearning.mapper.UserPaymentInfoMapper;
import springbootlearning.mapper.wsraspay.CreditCardMapper;
import springbootlearning.mapper.wsraspay.CustomerMapper;
import springbootlearning.mapper.wsraspay.OrderMapper;
import springbootlearning.mapper.wsraspay.PaymentMapper;
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
    private final MailIntegration mailIntegration;

    PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository,
                           WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
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

        // create the payment request
        OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.buildOrderDto(customerDto.getId(), paymentProcessDto));

        // process payment
        PaymentDto paymentDto = PaymentMapper.build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(paymentProcessDto.getUserPaymentInfoDto(), user.getCpf()));
        Boolean succesPayment = wsRaspayIntegration.processPayment(paymentDto);

        if (succesPayment) {
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(paymentProcessDto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);
            mailIntegration.sendMail(user.getEmail(), "mensagem para o usuario", "assunto da mensagem");
        }
        return null;
    }
}
