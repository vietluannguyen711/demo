package com.example.demo.converter;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    public User dtoToEntity(UserDto userDto) {

        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        return user;

    }

    public UserDto entityToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        userDto.setRoles(user.getRoles());

        return userDto;
    }
}