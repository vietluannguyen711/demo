package com.example.demo.service.impl;

import com.example.demo.converter.RoleConverter;
import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(RoleConverter::entityToDto).collect(Collectors.toList());
    }


}
