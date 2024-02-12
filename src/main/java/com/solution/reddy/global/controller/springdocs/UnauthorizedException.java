package com.solution.reddy.global.controller.springdocs;

import com.solution.reddy.global.message.DefaultMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UnauthorizedException {
    @Schema(description = "", nullable = true)
    String data = null;

    @Schema(description = "", example = "인증이 필요합니다.")
    String message = DefaultMessage.UNAUTHORIZED.getMessage();

    @Schema(description = "", example = "UNAUTHORIZED")
    String code = DefaultMessage.UNAUTHORIZED.toString();
}
