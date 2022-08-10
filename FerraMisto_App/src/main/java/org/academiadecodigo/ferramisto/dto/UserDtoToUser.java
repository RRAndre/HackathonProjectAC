package org.academiadecodigo.ferramisto.dto;

import org.academiadecodigo.ferramisto.persistence.models.User;
import org.academiadecodigo.ferramisto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser implements Converter<UserDto,User> {

    private UserService userService;

    @Autowired

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(UserDto userDto) {
        User user = (userDto.getId() != null ? userService.get(userDto.getId()) : new User());

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setMessage(userDto.getMessage());

        return user;
    }
}
