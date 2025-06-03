package com.example.couponjoa.common.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // JWT 에러 및 인증/인가 코드

    // 400
    UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "J001", "지원되지 않는 JWT 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "J002", "리프레시 토큰이 일치하지 않습니다."),
    UNAUTHORIZED_ROLE(HttpStatus.FORBIDDEN, "J003", "존재하지 않는 권한입니다."),

    // 회원가입 에러 코드
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "U001", "이미 존재하는 이메일입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
