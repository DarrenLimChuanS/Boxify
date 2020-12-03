package com.example.skeleton.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSummary {
    private Long userId;
    private String username;
    private String name;
    private String role;
}