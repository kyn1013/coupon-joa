package com.example.couponjoa.common.response;

import com.example.couponjoa.common.exception.constant.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String name;
    private String code;
    private int statusCode;
    private String message;

    private ErrorResponse(String name, String code, int statusCode, String message) {
        this.name = name;
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.name(), code.getCode(), code.getStatus().value(), code.getMessage());
    }
}
