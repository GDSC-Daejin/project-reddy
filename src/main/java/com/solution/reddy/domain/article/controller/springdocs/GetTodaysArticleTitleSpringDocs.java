package com.solution.reddy.domain.article.controller.springdocs;

import com.solution.reddy.domain.article.controller.springdocs.model.ArticleNoContents;
import com.solution.reddy.global.controller.springdocs.ServerErrorException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "오늘의 기사 title 조회 API", description = "오늘의 기사 title 조회 api 입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "204",
                description = "기사가 없습니다",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArticleNoContents.class))),
        @ApiResponse(
                responseCode = "500",
                description = "알수 없는 서버 에러 발생",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class))),
})
public @interface GetTodaysArticleTitleSpringDocs {
}
