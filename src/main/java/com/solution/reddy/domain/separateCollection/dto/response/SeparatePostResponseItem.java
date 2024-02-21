package com.solution.reddy.domain.separateCollection.dto.response;

import com.solution.reddy.domain.category.dto.CategoryResponseDto;
import lombok.Builder;

@Builder
public record SeparatePostResponseItem(
        CategoryResponseDto category,
        Long id,
        String imageUrl,
        String title
) {

}
