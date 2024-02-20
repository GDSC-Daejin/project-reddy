package com.solution.reddy.domain.article.dto.response;

import lombok.Builder;

@Builder
public record ArticleEmotionDto(
    Boolean isSosoEmotion,
    Boolean isAnalysisEmotion,
    Boolean isGoodEmotion,
    Boolean isEmpathyEmotion
) {
}
