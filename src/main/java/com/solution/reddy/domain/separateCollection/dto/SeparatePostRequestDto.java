package com.solution.reddy.domain.separateCollection.dto;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.separateCollection.entity.SeparateEntity;
import lombok.Builder;

@Builder
public record SeparatePostRequestDto(
    String title,
    String imageUrl,
    String description,
    String category
) {
    public SeparateEntity toSeparateEntity(CategoryEntity category) {
        return SeparateEntity.builder()
                .title(this.title)
                .imageUrl(this.imageUrl)
                .description(this.description)
                .category(category)
                .build();
    }
}
