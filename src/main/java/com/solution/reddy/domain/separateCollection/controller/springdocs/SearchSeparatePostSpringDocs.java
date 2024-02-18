package com.solution.reddy.domain.separateCollection.controller.springdocs;

import com.solution.reddy.domain.separateCollection.controller.springdocs.model.SeparatePostNoContents;
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
@Operation(summary = "분리수거 게시물 검색 API", description = "분리수거 게시물 검색 API 입니다. 제목, 내용 통합 검색입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "204",
                description = "게시물이 없습니다",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeparatePostNoContents.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "알수 없는 서버 에러 발생",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class))
        )
})
public @interface SearchSeparatePostSpringDocs {
}
