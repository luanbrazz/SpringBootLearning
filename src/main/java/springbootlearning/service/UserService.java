package springbootlearning.service;

import springbootlearning.dto.UserDto;
import springbootlearning.model.User;

public interface UserService {

    User create(UserDto userDto);
}
