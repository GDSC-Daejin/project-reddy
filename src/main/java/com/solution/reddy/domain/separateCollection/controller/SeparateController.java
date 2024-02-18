package com.solution.reddy.domain.separateCollection.controller;

import com.solution.reddy.domain.separateCollection.controller.springdocs.CreateSeparatePostSpringDocs;
import com.solution.reddy.domain.separateCollection.controller.springdocs.GetSeparatePostSpringDocs;
import com.solution.reddy.domain.separateCollection.controller.springdocs.SearchSeparatePostSpringDocs;
import com.solution.reddy.domain.separateCollection.dto.SeparatePostRequest;
import com.solution.reddy.domain.separateCollection.dto.SeparatePostSearchRequest;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostResponsePage;
import com.solution.reddy.domain.separateCollection.service.SeparateService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.message.SeparateMessage;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "분리수거 정보 게시물 관련 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class SeparateController {
    private final SeparateService separateService;

    @PostMapping("/separate")
    @CreateSeparatePostSpringDocs
    public ReddyApiResponse<Long> createSeparatePost(@RequestBody SeparatePostRequest requestDto) {
        Long responseId = separateService.createSeparatePost(requestDto);
        return ReddyApiResponse.createResponse(responseId, SeparateMessage.POST_CREATE_SUCCESS);
    }

    @GetMapping("/separate")
    @GetSeparatePostSpringDocs
    public ReddyApiResponse<SeparatePostResponsePage> getSeparateByCategory(@RequestParam Long category,
                                                                            @PageableDefault @Parameter(hidden = true) Pageable pageable) {
        SeparatePostResponsePage response = separateService.getSeparateByCategory(category, pageable);
        return ReddyApiResponse.createResponse(response, SeparateMessage.POST_GET_SUCCESS);
    }

    @PostMapping("/separate/search")
    @SearchSeparatePostSpringDocs
    public ReddyApiResponse<SeparatePostResponsePage> searchSeparatePost(@RequestBody SeparatePostSearchRequest request,
                                                                         @PageableDefault @Parameter(hidden = true) Pageable pageable) {
        SeparatePostResponsePage response = separateService.searchSeparatePost(request.keyword(), pageable);
        return ReddyApiResponse.createResponse(response, SeparateMessage.POST_SEARCH_SUCCESS);
    }
}
