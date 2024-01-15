package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/update")
    public String updateProfile(Model model, @AuthenticationPrincipal UserDetails currentUser) {

        User user = userRepository.findByUsername(currentUser.getUsername());
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if (userProfile == null){
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }

        model.addAttribute("userProfile", userProfile);
        return "user-profile-form";
    }


    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("userProfile") UserProfile userProfile, RedirectAttributes redirectAttributes) {

        userProfileService.saveUserProfile(userProfile);
        redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        return "redirect:/login";
    }

}