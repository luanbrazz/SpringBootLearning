package springbootlearning.mapper.wsraspay;

import springbootlearning.dto.wsraspay.CreditCardDto;
import springbootlearning.dto.wsraspay.PaymentDto;

public class PaymentMapper {

    public static PaymentDto build(String customerId, String orderId, CreditCardDto creditCardDto) {
        return PaymentDto.builder()
                .customerId(customerId)
                .orderId(orderId)
                .creditCard(creditCardDto)
                .build();
    }
}
