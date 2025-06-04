package com.example.couponjoa.domain.user.service;

import com.example.couponjoa.common.exception.constant.ErrorCode;
import com.example.couponjoa.common.exception.object.ClientException;
import com.example.couponjoa.domain.auth.dto.AuthUser;
import com.example.couponjoa.domain.user.dto.response.UserResponse;
import com.example.couponjoa.domain.user.entity.User;
import com.example.couponjoa.domain.user.enums.UserRole;
import com.example.couponjoa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse findUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ClientException(ErrorCode.USER_NOT_FOUND));
        return UserResponse.of(user.getUsername(), user.getEmail(), user.getUserRole().name());
    }
}
