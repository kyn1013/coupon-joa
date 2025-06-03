package com.example.couponjoa.domain.auth.service;

import com.example.couponjoa.common.config.JwtUtil;
import com.example.couponjoa.common.exception.constant.ErrorCode;
import com.example.couponjoa.common.exception.object.ClientException;
import com.example.couponjoa.domain.auth.dto.request.SigninRequest;
import com.example.couponjoa.domain.auth.dto.request.SignupRequest;
import com.example.couponjoa.domain.auth.dto.response.SigninResponse;
import com.example.couponjoa.domain.auth.dto.response.SignupResponse;
import com.example.couponjoa.domain.user.entity.User;
import com.example.couponjoa.domain.user.enums.UserRole;
import com.example.couponjoa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SigninResponse signin(SigninRequest signinRequest) {
        // 중복 이메일이 있는지 검증
        Boolean isMailExists = userRepository.existsByEmail(signinRequest.getEmail());

        if (isMailExists) {
            throw new ClientException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        String password = passwordEncoder.encode(signinRequest.getPassword());
        User user = User.of(signinRequest.getEmail(), password, signinRequest.getName(), UserRole.of(signinRequest.getUserRole()));
        User savedUser = userRepository.save(user);
        return SigninResponse.of(savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt());
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        User user = userRepository.findByEmail(signupRequest.getEmail()).orElseThrow(() -> new ClientException(ErrorCode.USER_NOT_FOUND));
        Boolean isMatched = passwordEncoder.matches(signupRequest.getPassword(), user.getPassword());

        if (!isMatched) {
           throw new ClientException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.createToken(user.getId(), user.getEmail(), user.getUserRole());
        return SignupResponse.of(token);
    }
}
