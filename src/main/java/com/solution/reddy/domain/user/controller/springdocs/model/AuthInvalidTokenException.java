package com.solution.reddy.domain.user.controller.springdocs.model;

import com.solution.reddy.global.message.AuthMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AuthInvalidTokenException {

    @Schema(description = "", nullable = true)
    String data = null;

    @Schema(description = "", example = "ID TOKEN이 유효하지 않습니다")
    String message = AuthMessage.INVALID_ID_TOKEN.getMessage();

    @Schema(description = "", example = "INVALID_TOKEN")
    String code = AuthMessage.INVALID_ID_TOKEN.toString();
}
