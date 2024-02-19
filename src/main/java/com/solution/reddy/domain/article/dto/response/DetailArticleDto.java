package com.solution.reddy.domain.article.dto.response;

import lombok.Builder;

@Builder
public record DetailArticleDto(
    String date,
    String title,
    String link,
    String contents,
    String articleImageUrl,
    Long sosoCount,
    Long analysisCount,
    Long goodCount,
    Long empathyCount
)
{ }
