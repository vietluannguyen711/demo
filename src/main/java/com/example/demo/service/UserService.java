package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    List<UserDto> getAllUsers();

    User updateUser(UserDto userDto);

    void deleteUser(Long userId);
}
