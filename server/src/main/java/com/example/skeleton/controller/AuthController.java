package com.example.skeleton.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import com.example.skeleton.exception.AppException;
import com.example.skeleton.model.Role;
import com.example.skeleton.model.RoleName;
import com.example.skeleton.model.User;
import com.example.skeleton.payload.ApiResponse;
import com.example.skeleton.payload.JwtAuthenticationResponse;
import com.example.skeleton.payload.LoginRequest;
import com.example.skeleton.payload.SignUpRequest;
import com.example.skeleton.repository.RoleRepository;
import com.example.skeleton.repository.UserRepository;
import com.example.skeleton.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private JwtTokenProvider tokenProvider;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.generateToken(authentication);
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }

        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                                        HttpStatus.BAD_REQUEST);
                }

                // Creating user's account
                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new AppException("User Role not set."));

                user.setRoles(Collections.singleton(userRole));

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();

                return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
        }
}
