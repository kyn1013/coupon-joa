package com.example.couponjoa.common.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통 사용 에러 코드

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "서버 에러가 발생했습니다."),

    // JWT 에러 코드

    // 400
    UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "J001", "지원되지 않는 JWT 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "J002", "리프레시 토큰이 일치하지 않습니다."),
    UNAUTHORIZED_ROLE(HttpStatus.FORBIDDEN, "J003", "존재하지 않는 권한입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "J004", "만료된 JWT 토큰입니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "J005", "유효하지 않는 JWT 서명입니다."),

    // 회원가입 및 로그인 에러 코드

    //400
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "U001", "이미 존재하는 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U002", "존재하지 않는 회원입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "U003", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
