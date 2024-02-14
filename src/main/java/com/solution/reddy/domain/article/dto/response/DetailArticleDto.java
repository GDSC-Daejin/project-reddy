package com.solution.reddy.domain.article.dto.response;

import lombok.Builder;

@Builder
public record DetailArticleDto(
    String title,
    String contents,
    Long goodCount,
    Long sadCount,
    Long angryCount,
    Long sosoCount
)
{ }
