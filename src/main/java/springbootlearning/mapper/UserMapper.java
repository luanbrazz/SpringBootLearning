package springbootlearning.mapper;

import springbootlearning.dto.UserDto;
import springbootlearning.model.SubscriptionType;
import springbootlearning.model.User;
import springbootlearning.model.UserType;

public class UserMapper {

    public static User fromDtoToEntity(UserDto userDto, UserType userType, SubscriptionType subscriptionType) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .cpf(userDto.getCpf())
                .dtExpiration(userDto.getDtExpiration())
                .dtSubscription(userDto.getDtSubscription())
                .subscriptionType(subscriptionType)
                .userType(userType)
                .build();
    }
}
