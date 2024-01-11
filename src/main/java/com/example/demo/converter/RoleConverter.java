package com.example.demo.converter;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public Role dtoToEntity(RoleDto roleDto){

        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;

    }

    public static RoleDto entityToDto(Role role){

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;

    }
}
