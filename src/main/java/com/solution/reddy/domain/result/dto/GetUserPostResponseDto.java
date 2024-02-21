package com.solution.reddy.domain.result.dto;

import lombok.Builder;

@Builder
public record GetUserPostResponseDto(
        Long resultPostId,
        String resultTitle,
        String imageUrl
) {
}
