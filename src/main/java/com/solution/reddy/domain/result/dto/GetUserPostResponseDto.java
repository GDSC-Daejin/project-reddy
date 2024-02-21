package com.solution.reddy.domain.result.dto;

import lombok.Builder;

@Builder
public record GetUserPostResponseDto(
        Long resultGroupId,
        String resultTitle,
        String imageUrl
) {
}
