package springbootlearning.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.dto.wsraspay.OrderDto;

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
}
