package springbootlearning.integration.impl;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public WsRaspayIntegrationImpl() {
        this.restTemplate = new RestTemplate();
        this.headers = this.getHttpHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        try {
            HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto, this.headers);

            ResponseEntity<CustomerDto> response = restTemplate.exchange("http://localhost:8081/ws-raspay/v1/customer",
                    HttpMethod.POST, request, CustomerDto.class);
            return response.getBody();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        try {
            HttpEntity<OrderDto> request = new HttpEntity<>(orderDto, this.headers);

            ResponseEntity<OrderDto> response = restTemplate.exchange("http://localhost:8081/ws-raspay/v1/order",
                    HttpMethod.POST, request, OrderDto.class);
            return response.getBody();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Boolean processPayment(PaymentDto paymentDto) {
        try {
            HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto, this.headers);

            ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8081/ws-raspay/v1/payment/credit-card/",
                    HttpMethod.POST, request, Boolean.class);
            return response.getBody();
        } catch (Exception exception) {
            throw exception;
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credential = "appuser:apppassword";
        String base64 = new String(Base64.encodeBase64(credential.getBytes()));
        headers.add("Authorization", "Basic " + base64);
        return headers;
    }
}
