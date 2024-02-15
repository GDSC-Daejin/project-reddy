package com.solution.reddy.domain.separateCollection.dto.response;

import com.solution.reddy.domain.category.dto.CategoryResponseDto;
import lombok.Builder;

@Builder
public record SeparatePostResponseItem(
        CategoryResponseDto category,
        String imageUrl,
        String title,
        String description
) {

}
