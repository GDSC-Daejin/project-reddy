package com.solution.reddy.domain.result.dto;

import lombok.Builder;

@Builder
public record AIResultResponse(
        Long id,
        String title,
        String description
) {
}
