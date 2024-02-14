package com.solution.reddy.domain.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
    Long id,
    String categoryName
) { }
