package com.example.couponjoa.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "기존 비밀번호는 필수 입력 값입니다.")
    private String oldPassword;

    @NotBlank(message = "새로운 비밀번호는 필수 입력 값입니다.")
    private String newPassword;
}
