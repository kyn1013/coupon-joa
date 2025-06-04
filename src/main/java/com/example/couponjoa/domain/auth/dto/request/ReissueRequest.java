package com.example.couponjoa.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReissueRequest {

    @NotBlank(message = "Refresh Token은 필수 입력 값입니다.")
    private String refreshToken;
}
