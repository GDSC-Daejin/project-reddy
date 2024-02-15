package com.solution.reddy.domain.separateCollection.dto.response;

import com.solution.reddy.domain.category.entity.CategoryEntity;

public record SeparatePostResponseDto(
        CategoryEntity category,
        String imageUrl,
        String title,
        String description
) {

}
