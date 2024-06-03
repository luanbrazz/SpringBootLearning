package springbootlearning.integration.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.dto.wsraspay.OrderDto;
import springbootlearning.dto.wsraspay.PaymentDto;
import springbootlearning.integration.WsRaspayIntegration;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    private RestTemplate restTemplate;

    public WsRaspayIntegrationImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        try {
            HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto);

            ResponseEntity<CustomerDto> response = restTemplate.exchange("http://localhost:8081/ws-raspay/v1/customer",
                    HttpMethod.POST, request, CustomerDto.class);
            return response.getBody();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public Boolean processPayment(PaymentDto paymentDto) {
        return null;
    }
}
