package com.example.couponjoa.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    private final String token;

    private SignupResponse(String token) {
        this.token = token;
    }

    public static SignupResponse of(String token) {
        return new SignupResponse(token);
    }
}
