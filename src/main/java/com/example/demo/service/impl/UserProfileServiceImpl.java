package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findByUser(User user) {
        return userProfileRepository.findByUser(user);
    }


}
