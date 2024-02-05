package com.solution.reddy.domain.user.controller;

import com.solution.reddy.domain.user.dto.request.SocialLoginRequest;
import com.solution.reddy.domain.user.service.AuthService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.jwt.dto.TokenResponse;
import com.solution.reddy.global.message.UserMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "사용자 인증 관련 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/google")
    public ReddyApiResponse<TokenResponse> googleLogin(@RequestBody SocialLoginRequest socialLoginRequest) {
        TokenResponse tokenResponse = authService.googleLogin(socialLoginRequest.token());
        return ReddyApiResponse.createResponse(tokenResponse, UserMessage.LOGIN_SUCCESS);
    }

    @PostMapping("/auth/logintest")
    public ReddyApiResponse<TokenResponse>testLogin() {
        String testEmail = "test@test.com";
        TokenResponse tokenResponse = authService.testLogin(testEmail);
        return ReddyApiResponse.createResponse(tokenResponse, UserMessage.LOGIN_SUCCESS);
    }


}
