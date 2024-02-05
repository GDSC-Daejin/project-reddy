package com.solution.reddy.domain.user.controller;

import com.solution.reddy.domain.user.dto.request.SocialLoginRequest;
import com.solution.reddy.domain.user.dto.request.LogoutRequest;
import com.solution.reddy.domain.user.dto.request.ReissueRequest;
import com.solution.reddy.domain.user.service.AuthService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.jwt.dto.TokenResponse;
import com.solution.reddy.global.jwt.dto.UserInfo;
import com.solution.reddy.global.message.UserMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/auth/test")
    public ReddyApiResponse<TokenResponse>testLogin() {
        String testEmail = "test@test.com";
        TokenResponse tokenResponse = authService.testLogin(testEmail);
        return ReddyApiResponse.createResponse(tokenResponse, UserMessage.LOGIN_SUCCESS);
    }

    @PostMapping("/token")
    public ReddyApiResponse<TokenResponse> reissue(@AuthenticationPrincipal UserInfo user, @RequestBody ReissueRequest request) {
        TokenResponse tokenResponse = authService.reissue(user.getProvider(), user.getEmail(), request.refreshToken());
        return ReddyApiResponse.createResponse(tokenResponse, UserMessage.REISSUE_SUCCESS);
    }

    @DeleteMapping("/auth/user")
    public ReddyApiResponse<?> logout(@AuthenticationPrincipal UserInfo user, @RequestBody LogoutRequest logoutRequest) {
        authService.logout(user.getProvider(), user.getEmail() , logoutRequest.accessToken());
        return ReddyApiResponse.createResponse(null, UserMessage.LOGOUT_SUCCESS);
    }
}
