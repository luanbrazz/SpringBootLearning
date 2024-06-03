package springbootlearning.integration;

import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.dto.wsraspay.OrderDto;
import springbootlearning.dto.wsraspay.PaymentDto;

public interface WsRaspayIntegration {

    CustomerDto createCustomer(CustomerDto customerDto);

    OrderDto createOrder(OrderDto orderDto);

    Boolean processPayment(PaymentDto paymentDto);
}
