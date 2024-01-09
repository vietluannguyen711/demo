package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(UserDto userDto, String roleName);

    User findByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserDto> findAll();
}
