package com.example.demo.controller;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/dashboard")
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/admin")
    public String home() {
        return "dashboard/admin";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new User());
        model.addAttribute("roles", roleService.findAll());
        return "dashboard/registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto, @RequestParam("selectedRoles") Long[] selectedRoles, Model model, RedirectAttributes redirectAttributes) {
        Set<Role> roles = new HashSet<>();
        for (Long selectedRole : selectedRoles) {
            Role role = roleService.findById(selectedRole);
            if (role != null) {
                roles.add(role);
            }
        }

        userDto.setRoles(roles);

        User user = userConverter.dtoToEntity(userDto);

        try {
            userService.saveUser(user);
        } catch (Exception e) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("message", e.getMessage());
            return "dashboard/registration";
        }
        redirectAttributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "dashboard/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        userRepository.delete(user);

        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/dashboard/users";
    }

}