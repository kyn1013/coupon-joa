package com.example.couponjoa.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    private final String accessToken;
    private final String refreshToken;

    private SignupResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static SignupResponse of(String accessToken, String refreshToken) {
        return new SignupResponse(accessToken, refreshToken);
    }
}
