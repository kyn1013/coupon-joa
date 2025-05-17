package com.example.couponjoa.common.response;

import com.example.couponjoa.common.exception.constant.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String name;
    private int code;
    private String message;

    private ErrorResponse(String name, int code, String message) {
        this.name = name;
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.name(), code.getStatus().value(), code.getMessage());
    }
}
