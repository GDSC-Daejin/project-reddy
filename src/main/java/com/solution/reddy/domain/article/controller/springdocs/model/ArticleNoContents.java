package com.solution.reddy.domain.article.controller.springdocs.model;

import com.solution.reddy.global.message.ArticleMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter

public class ArticleNoContents {
    @Schema(description = "", nullable = true)
    String data = null;

    @Schema(description = "", example = "기사 정보가 없습니다.")
    String message = ArticleMessage.ARTICLE_IS_EMPTY.getMessage();

    @Schema(description = "", example = "ARTICLE_IS_EMPTY")
    String code = ArticleMessage.ARTICLE_IS_EMPTY.toString();
}