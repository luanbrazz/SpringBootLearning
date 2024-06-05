package springbootlearning.mapper.wsraspay;

import springbootlearning.dto.wsraspay.CustomerDto;
import springbootlearning.model.User;

public class CustomerMapper {

    public static CustomerDto build(User user) {
        var fullName = user.getName().split(" ");
        var firstName = fullName[0];
        var lastName = fullName.length > 1 ? fullName[fullName.length - 1] : "";

        return CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .cpf(user.getCpf())
                .email(user.getEmail())
                .build();
    }
}
