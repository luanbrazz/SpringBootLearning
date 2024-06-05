package springbootlearning.mapper.wsraspay;

import springbootlearning.dto.UserPaymentInfoDto;
import springbootlearning.dto.wsraspay.CreditCardDto;

public class CreditCardMapper {

    public static CreditCardDto build(UserPaymentInfoDto userPaymentInfoDto, String documentNumber) {
        return CreditCardDto.builder()
                .documentNumber(documentNumber)
                .cvv(Long.parseLong(userPaymentInfoDto.getCardSecurityCode()))
                .number(userPaymentInfoDto.getCardNumber())
                .month(userPaymentInfoDto.getCardExpirationMonth())
                .year(userPaymentInfoDto.getCardExpirationYear())
                .installments(userPaymentInfoDto.getInstalments())
                .build();
    }
}
