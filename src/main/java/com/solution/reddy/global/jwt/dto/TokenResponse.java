package com.solution.reddy.global.jwt.dto;

public record TokenResponse(
        String bearerType,
        String accessToken,
        String refreshToken,
        Long accessTokenExpiresIn
) {
}
