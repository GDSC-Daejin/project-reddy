package com.solution.reddy.domain.article.dto.request;


public record ArticleEmotionRequest(
    ArticleEmotion emotion,
    Long articleId
) {
}
