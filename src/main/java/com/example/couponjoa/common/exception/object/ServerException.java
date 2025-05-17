package com.example.couponjoa.common.exception.object;

import com.example.couponjoa.common.exception.constant.ErrorCode;
import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {
    private ErrorCode errorCode;

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
