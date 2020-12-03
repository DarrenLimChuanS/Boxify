package com.example.skeleton.controller;

import com.example.skeleton.payload.UserProfile;
import com.example.skeleton.payload.UserSummary;
import com.example.skeleton.security.CurrentUser;
import com.example.skeleton.security.UserPrincipal;
import com.example.skeleton.service.UserService;
import com.example.skeleton.util.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getCurrentUser(currentUser.getId());
    }

    @GetMapping("/checkUsernameAvailability")
    public Boolean checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return !userService.existsByUsername(username);
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getUserProfile(username, page, size);
    }
}