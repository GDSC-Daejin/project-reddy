package com.solution.reddy.domain.separateCollection.dto.response;

import lombok.Builder;

@Builder
public record SeparatePostDetailDto(
        Long id,
        String imageUrl,
        String title,
        String description
) {
}
