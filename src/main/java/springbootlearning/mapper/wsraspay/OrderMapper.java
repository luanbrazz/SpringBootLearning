package springbootlearning.mapper.wsraspay;

import springbootlearning.dto.PaymentProcessDto;
import springbootlearning.dto.wsraspay.OrderDto;

public class OrderMapper {

    public static OrderDto buildOrderDto(String customerId, PaymentProcessDto paymentProcessDto) {
        return OrderDto.builder()
                .customerId(customerId)
                .productAcronym(paymentProcessDto.getProductKey())
                .discount(paymentProcessDto.getDiscount())
                .build();
    }
}
