package com.solution.reddy.domain.result.controller;

import com.solution.reddy.domain.result.dto.SaveCheckResultRequest;
import com.solution.reddy.domain.result.service.ResultService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.jwt.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FirstVersionRestController
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    @PostMapping("/check")
    public ReddyApiResponse<?> checkImageWithAI(@RequestBody String imageUrl) {

        return null;
    }

    @PostMapping("/check/save")
    public ReddyApiResponse<?> saveCheckResult(@RequestBody SaveCheckResultRequest request,
                                               @AuthenticationPrincipal UserInfo user) {

        resultService.saveCheckResult(request, user.getEmail());
        return null;
    }
}
