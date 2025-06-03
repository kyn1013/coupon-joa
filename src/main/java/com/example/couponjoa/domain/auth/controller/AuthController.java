package com.example.couponjoa.domain.auth.controller;

import com.example.couponjoa.common.response.CommonResponse;
import com.example.couponjoa.domain.auth.dto.request.SigninRequest;
import com.example.couponjoa.domain.auth.dto.request.SignupRequest;
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

    @PostMapping("/api/v1/auth/signin")
    public ResponseEntity<CommonResponse> signin(@RequestBody @Valid SigninRequest signinRequest){
        SigninResponse signinResponse = authService.signin(signinRequest);
        return ResponseEntity.ok().body(CommonResponse.of(signinResponse));
    }

    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<CommonResponse> signup(@RequestBody @Valid SignupRequest signupRequest){
        SignupResponse signupResponse = authService.signup(signupRequest);
        return ResponseEntity.ok().body(CommonResponse.of(signupResponse));
    }

}
