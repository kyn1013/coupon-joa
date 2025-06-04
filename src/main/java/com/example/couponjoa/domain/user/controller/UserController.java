package com.example.couponjoa.domain.user.controller;

import com.example.couponjoa.common.response.CommonResponse;
import com.example.couponjoa.domain.auth.dto.AuthUser;
import com.example.couponjoa.domain.user.dto.response.UserResponse;
import com.example.couponjoa.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
     * 마이페이지 조회
     */
    @Secured("ROLE_USER")
    @GetMapping("/api/v1/users")
    public ResponseEntity<CommonResponse> findUser(@AuthenticationPrincipal AuthUser authUser) {
        UserResponse response = userService.findUser(authUser.getUserId());
        return ResponseEntity.ok().body(CommonResponse.of(response));
    }

}
