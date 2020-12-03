package com.example.skeleton.service;

import java.util.Set;

import com.example.skeleton.exception.AppException;
import com.example.skeleton.exception.ResourceNotFoundException;
import com.example.skeleton.model.Role;
import com.example.skeleton.model.RoleName;
import com.example.skeleton.model.User;
import com.example.skeleton.payload.UserProfile;
import com.example.skeleton.payload.UserSummary;
import com.example.skeleton.repository.RoleRepository;
import com.example.skeleton.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));
    }

    public User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
    }

    public UserSummary getCurrentUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        return new UserSummary(user.getId(), user.getUsername(), user.getName(), user.getRoles().iterator().next().getName().name());
    }

    public UserProfile getUserProfile(String username, int page, int size) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }
}