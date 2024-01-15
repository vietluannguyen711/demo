package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;

public interface UserProfileService {
    UserProfile saveUserProfile(UserProfile userProfile);

    UserProfile findByUser(User user);

}
