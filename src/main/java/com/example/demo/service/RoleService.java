package com.example.demo.service;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    Role findByName(String name);

    List<RoleDto> findAll();
}
