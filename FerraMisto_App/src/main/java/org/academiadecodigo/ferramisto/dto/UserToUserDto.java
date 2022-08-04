package org.academiadecodigo.ferramisto.dto;


import org.academiadecodigo.ferramisto.persistence.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto extends AbstractConverter<User, UserDto>{


    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        user.setPhone(user.getPhone());

        return userDto;
    }
}
