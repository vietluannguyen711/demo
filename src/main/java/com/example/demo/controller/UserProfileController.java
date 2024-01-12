package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;
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

@Controller
@RequestMapping("/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @GetMapping("/update")
    public String updateProfile(Model model, @AuthenticationPrincipal UserDetails currentUser) {

        User user = userService.findByUsername(currentUser.getUsername());
        UserProfile userProfile = user.getUserProfile();
        if (userProfile == null){
            userProfile = new UserProfile();

        }
        model.addAttribute("userProfile", userProfile);
        return "user-profile-form";
    }


    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("userProfile") UserProfile userProfile) {

        UserProfile userProfileSave = userProfileService.saveUserProfile(userProfile);
        return "redirect:/login";
    }

}