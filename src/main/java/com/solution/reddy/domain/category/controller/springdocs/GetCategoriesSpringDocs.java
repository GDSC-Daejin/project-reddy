package com.solution.reddy.domain.category.controller.springdocs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "카테고리 조회 API", description = "카테고리 전체 조회 API 입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
})
public @interface GetCategoriesSpringDocs {
}
