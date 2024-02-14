package com.solution.reddy.domain.separateCollection.controller;

import com.solution.reddy.domain.separateCollection.service.SeparateService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "분리수거 정보 게시물 관련 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class SeparateController {
    private final SeparateService separateService;

}
