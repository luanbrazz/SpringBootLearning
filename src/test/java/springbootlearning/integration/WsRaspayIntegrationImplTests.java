package springbootlearning.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootlearning.dto.wsraspay.CreditCardDto;
import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.dto.wsraspay.OrderDto;
import springbootlearning.dto.wsraspay.PaymentDto;

import java.math.BigDecimal;

@SpringBootTest
public class WsRaspayIntegrationImplTests {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;

    @Test
    void createCustomerWhenDtoOK() {
        CustomerDto customerDto = new CustomerDto(null, "teste@teste.com", "Luan", "Braz", "15218051055");
        wsRaspayIntegration.createCustomer(customerDto);
    }

    @Test
    void createOrderWhenDtoOK() {
        OrderDto orderDto = new OrderDto(null, "665dafb53f1e9e63fdbd2b9e", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDto);
    }

    @Test
    void processPaymentWhenDtoOK() {
        CreditCardDto creditCardDto = new CreditCardDto(123L, "15218051055", 0L, 06L, "123445678956", 2025L);
        PaymentDto paymentDto = new PaymentDto("665dafb53f1e9e63fdbd2b9e", "665de732c174b8563fbad4c7", creditCardDto);
        wsRaspayIntegration.processPayment(paymentDto);
    }
}
