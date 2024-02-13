package com.solution.reddy.domain.article.dto.response;

import lombok.Builder;

@Builder
public record ArticleTitleItems (
        Long id,
        String title
)
{ }
