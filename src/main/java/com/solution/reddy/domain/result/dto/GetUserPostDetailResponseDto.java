package com.solution.reddy.domain.result.dto;

import lombok.Builder;

@Builder
public record GetUserPostDetailResponseDto(
        String imageUrl,
        String resultTitle,
        String description
) {
}
