package com.example.couponjoa.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserResponse {

    private final String name;

    private final String email;

    private final String userRole;

    private UserResponse(String name, String email, String userRole) {
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }

    public static UserResponse of(String name, String email, String userRole) {
        return new UserResponse(name, email, userRole);
    }
}
