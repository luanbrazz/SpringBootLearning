package springbootlearning.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootlearning.dto.wsraspay.CustomerDto;

@SpringBootTest
public class WsRaspayIntegrationImplTests {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;

    @Test
    void createCustomerWhenDtoOK() {
        CustomerDto customerDto = new CustomerDto(null, "teste@teste.com", "Luan", "Braz", "15218051055");
        wsRaspayIntegration.createCustomer(customerDto);
    }
}
