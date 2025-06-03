package com.example.couponjoa.domain.auth.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SigninResponse {

    private final String name;

    private final String email;

    private final LocalDateTime createdDate;

    private SigninResponse(String name, String email, LocalDateTime createdDate) {
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
    }

    public static SigninResponse of(String name, String email, LocalDateTime createdDate) {
        return new SigninResponse(name, email, createdDate);
    }
}
