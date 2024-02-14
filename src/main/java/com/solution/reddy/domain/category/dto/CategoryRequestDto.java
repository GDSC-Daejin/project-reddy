package com.solution.reddy.domain.category.dto;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
        String CategoryName
) {
    public CategoryEntity makeCategoryEntity() {
        return CategoryEntity.builder()
                .categoryName(this.CategoryName)
                .build();
    }
}
