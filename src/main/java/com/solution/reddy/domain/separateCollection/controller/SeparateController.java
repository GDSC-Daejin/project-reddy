package com.solution.reddy.domain.separateCollection.controller;

import com.solution.reddy.domain.separateCollection.service.SeparateService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import lombok.RequiredArgsConstructor;

@FirstVersionRestController
@RequiredArgsConstructor
public class SeparateController {
    private final SeparateService separateService;

}
