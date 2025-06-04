package com.example.couponjoa.domain.auth.controller;

import com.example.couponjoa.common.response.CommonResponse;
import com.example.couponjoa.domain.auth.dto.request.ReissueRequest;
import com.example.couponjoa.domain.auth.dto.request.SigninRequest;
import com.example.couponjoa.domain.auth.dto.request.SignupRequest;
import com.example.couponjoa.domain.auth.dto.response.ReissueResponse;
import com.example.couponjoa.domain.auth.dto.response.SigninResponse;
import com.example.couponjoa.domain.auth.dto.response.SignupResponse;
import com.example.couponjoa.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /*
     * 회원가입
     */
    @PostMapping("/api/v1/auth/signin")
    public ResponseEntity<CommonResponse> signin(@RequestBody @Valid SigninRequest signinRequest){
        SigninResponse signinResponse = authService.signin(signinRequest);
        return ResponseEntity.ok().body(CommonResponse.of(signinResponse));
    }

    /*
     * 로그인
     */
    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<CommonResponse> signup(@RequestBody @Valid SignupRequest signupRequest){
        SignupResponse signupResponse = authService.signup(signupRequest);
        return ResponseEntity.ok().body(CommonResponse.of(signupResponse));
    }

    /*
     * 토큰 재발급 (access, refresh)
     */
    @PostMapping("/api/v1/auth/tokens")
    public ResponseEntity<CommonResponse> reissueToken(@RequestBody @Valid ReissueRequest reissueRequest){
        ReissueResponse response = authService.reissueToken(reissueRequest);
        return ResponseEntity.ok().body(CommonResponse.of(response));
    }

}
