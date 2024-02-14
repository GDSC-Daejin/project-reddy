package com.solution.reddy.domain.article.controller.springdocs.model;

import com.solution.reddy.global.message.ArticleMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ArticleNotFoundException {
    @Schema(description = "", nullable = true)
    String data = null;

    @Schema(description = "", example = "기사 정보를 찾을 수 없습니다.")
    String message = ArticleMessage.ARTICLE_NOT_FOUD.getMessage();

    @Schema(description = "", example = "ARTICLE_NOT_FOUD")
    String code = ArticleMessage.ARTICLE_NOT_FOUD.toString();
}
