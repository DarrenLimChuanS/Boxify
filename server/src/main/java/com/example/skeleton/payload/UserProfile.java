package com.example.skeleton.payload;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private Long userId;
    private String username;
    private String name;
    private Instant joinedAt;

}