package springbootlearning.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String customerId;

    private String orderId;

    private CreditCardDto creditCard;
}
