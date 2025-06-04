package com.example.couponjoa.domain.auth.service;

import com.example.couponjoa.common.config.JwtUtil;
import com.example.couponjoa.common.exception.constant.ErrorCode;
import com.example.couponjoa.common.exception.object.ClientException;
import com.example.couponjoa.common.utils.RedisUtils;
import com.example.couponjoa.domain.auth.dto.request.ReissueRequest;
import com.example.couponjoa.domain.auth.dto.request.SigninRequest;
import com.example.couponjoa.domain.auth.dto.request.SignupRequest;
import com.example.couponjoa.domain.auth.dto.response.ReissueResponse;
import com.example.couponjoa.domain.auth.dto.response.SigninResponse;
import com.example.couponjoa.domain.auth.dto.response.SignupResponse;
import com.example.couponjoa.domain.user.entity.User;
import com.example.couponjoa.domain.user.enums.UserRole;
import com.example.couponjoa.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RedisUtils redisUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final long REFRESH_TOKEN_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일

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

        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail(), user.getUserRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getId(), user.getEmail(), user.getUserRole());

        // refresh token을 서버 측에도 저장
        String key = "refresh:user:" + user.getId();
        redisUtils.setData(key, refreshToken, REFRESH_TOKEN_TIME);

        return SignupResponse.of(accessToken, refreshToken);
    }

    public ReissueResponse reissueToken(ReissueRequest reissueRequest) {
        String token = jwtUtil.substringToken(reissueRequest.getRefreshToken());
        Claims claims = jwtUtil.extractClaims(token);
        Long userId = Long.valueOf(claims.getSubject());

        String key = "refresh:user:" + userId;
        String savedToken = redisUtils.getData(key);

        // 리프레시 토큰이 일치하지 않는다면
        if (!savedToken.equals(reissueRequest.getRefreshToken())) {
            throw new ClientException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 리프레시 토큰이 일치한다면
        redisUtils.deleteData(key);
        User user = userRepository.findById(userId).orElseThrow(() -> new ClientException(ErrorCode.USER_NOT_FOUND));
        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail(), user.getUserRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getId(), user.getEmail(), user.getUserRole());
        redisUtils.setData(key, refreshToken, REFRESH_TOKEN_TIME);
        return ReissueResponse.of(accessToken, refreshToken);
    }
}
