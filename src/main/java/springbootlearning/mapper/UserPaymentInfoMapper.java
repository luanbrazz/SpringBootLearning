package springbootlearning.mapper;

import springbootlearning.dto.UserPaymentInfoDto;
import springbootlearning.model.User;
import springbootlearning.model.UserPaymentInfo;

public class UserPaymentInfoMapper {

    public static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDto userPaymentInfoDto, User user) {
        return UserPaymentInfo.builder()
                .id(userPaymentInfoDto.getId())
                .cardNumber(userPaymentInfoDto.getCardNumber())
                .cardExpirationMonth(userPaymentInfoDto.getCardExpirationMonth())
                .cardExpirationYear(userPaymentInfoDto.getCardExpirationYear())
                .cardSecurityCode(userPaymentInfoDto.getCardSecurityCode())
                .price(userPaymentInfoDto.getPrice())
                .dtPayment(userPaymentInfoDto.getDtPayment())
                .installments(userPaymentInfoDto.getInstalments())
                .user(user)
                .build();
    }
}
