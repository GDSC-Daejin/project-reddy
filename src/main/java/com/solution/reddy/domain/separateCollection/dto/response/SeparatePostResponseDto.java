package com.solution.reddy.domain.separateCollection.dto.response;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import lombok.Getter;

@Getter
public record SeparatePostResponseDto(
        CategoryEntity category,
        String imageUrl,
        String title,
        String description
) {

}
