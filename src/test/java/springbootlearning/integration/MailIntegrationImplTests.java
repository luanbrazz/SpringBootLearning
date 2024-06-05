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
public class MailIntegrationImplTests {

    @Autowired
    private MailIntegration mailIntegration;

    @Test
    void createCustomerWhenDtoOK() {
        CustomerDto customerDto = new CustomerDto(null, "teste@teste.com", "Luan", "Braz", "15218051055");
        mailIntegration.sendMail("teste@gmail.com", "mensagem", "Assunto");
    }

}
