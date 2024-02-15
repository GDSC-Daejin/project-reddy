package com.solution.reddy.domain.separateCollection.controller;

import com.solution.reddy.domain.separateCollection.dto.SeparatePostRequestDto;
import com.solution.reddy.domain.separateCollection.service.SeparateService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.message.SeparateMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "분리수거 정보 게시물 관련 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class SeparateController {
    private final SeparateService separateService;

    @PostMapping("/separate")
    public ReddyApiResponse<Long> createSeparatePost(@RequestBody SeparatePostRequestDto requestDto) {
        Long responseId = separateService.createSeparatePost(requestDto);
        return ReddyApiResponse.createResponse(responseId, SeparateMessage.POST_CREATE_SUCCESS);
    }

}
