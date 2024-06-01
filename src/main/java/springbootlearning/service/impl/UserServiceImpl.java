package springbootlearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootlearning.dto.UserDto;
import springbootlearning.exception.BadRequestException;
import springbootlearning.exception.NotFoundException;
import springbootlearning.mapper.UserMapper;
import springbootlearning.model.User;
import springbootlearning.model.UserType;
import springbootlearning.repository.UserRepository;
import springbootlearning.repository.UserTypeRepository;
import springbootlearning.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public User create(UserDto userDto) {

        if (Objects.nonNull(userDto.getId())) {
            throw new BadRequestException("id dever ser null");
        }

        var userTypeOpt = userTypeRepository.findById(userDto.getUserTypeId());
        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("UserTypeId " + userDto.getId() + " not found");
        }

        UserType userType = userTypeOpt.get();

        User user = UserMapper.fromDtoToEntity(userDto, userType, null);
        
        return userRepository.save(user);
    }
}
