package com.solution.reddy.domain.separateCollection.controller.springdocs.model;

import com.solution.reddy.global.message.SeparateMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter

public class SeparatePostNoContents {
    @Schema(description = "", nullable = true)
    String data = null;

    @Schema(description = "", example = "기사 정보가 없습니다.")
    String message = SeparateMessage.POST_NO_CONTENTS.getMessage();

    @Schema(description = "", example = "ARTICLE_IS_EMPTY")
    String code = SeparateMessage.POST_NO_CONTENTS.toString();
}