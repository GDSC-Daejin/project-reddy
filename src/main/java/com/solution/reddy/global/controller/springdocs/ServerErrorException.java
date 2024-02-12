package com.solution.reddy.global.controller.springdocs;

import static com.solution.reddy.global.message.DefaultMessage.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ServerErrorException {

    @Schema(description = "", nullable = true)
    String data = null;

    @Schema(description = "", example = "알수없는 서버 에러입니다.")
    String message = INTERNAL_SERVER_ERROR.getMessage();

    @Schema(description = "", example = "INTERNAL_SERVER_ERROR")
    String code = INTERNAL_SERVER_ERROR.toString();
}
