package com.example.couponjoa.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class ReissueResponse {

    private final String accessToken;
    private final String refreshToken;

    private ReissueResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static ReissueResponse of(String accessToken, String refreshToken) {
        return new ReissueResponse(accessToken, refreshToken);
    }

}
