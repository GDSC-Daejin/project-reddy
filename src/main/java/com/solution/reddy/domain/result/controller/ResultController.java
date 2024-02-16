package com.solution.reddy.domain.result.controller;

import com.solution.reddy.domain.result.service.ResultService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import lombok.RequiredArgsConstructor;

@FirstVersionRestController
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

}
