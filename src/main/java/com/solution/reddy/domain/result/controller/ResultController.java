package com.solution.reddy.domain.result.controller;

import com.solution.reddy.domain.result.controller.springdocs.CreateResultPostSpringDocs;
import com.solution.reddy.domain.result.controller.springdocs.GetResultPostDetailSpringDocs;
import com.solution.reddy.domain.result.dto.AIResultRequest;
import com.solution.reddy.domain.result.dto.AIResultResponse;
import com.solution.reddy.domain.result.dto.GetUserPostDetailResponseDto;
import com.solution.reddy.domain.result.dto.GetUserPostResponseDto;
import com.solution.reddy.domain.result.dto.SaveCheckResultRequest;
import com.solution.reddy.domain.result.service.ResultService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.jwt.dto.UserInfo;
import com.solution.reddy.global.message.ResultMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "카메라 결과 관련 컨트롤러(ai 모델 사용하는 곳)")
@FirstVersionRestController
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    @PostMapping("/check")
    public ReddyApiResponse<AIResultResponse> checkImageWithAI(@RequestBody AIResultRequest request) {
        AIResultResponse resultResponse = resultService.checkImageWithAI(request);
        return ReddyApiResponse.createResponse(resultResponse, ResultMessage.RESULT_GET_SUCCESS);
    }

    @PostMapping("/check/save")
    @CreateResultPostSpringDocs
    public ReddyApiResponse<?> saveResultPost(@RequestBody SaveCheckResultRequest request,
                                              @AuthenticationPrincipal UserInfo user) {
        resultService.saveCheckResult(request, user.getEmail());
        return ReddyApiResponse.createResponse("", ResultMessage.RESULT_SAVE_SUCCESS);
    }

    @GetMapping("/check")
    public ReddyApiResponse<List<GetUserPostResponseDto>> getResultPost(@AuthenticationPrincipal UserInfo user) {
        List<GetUserPostResponseDto> response = resultService.getResultPostByUser(user.getEmail());
        return ReddyApiResponse.createResponse(response, ResultMessage.RESULT_GET_SUCCESS);
    }

    @GetMapping("/check/{groupId}")
    @GetResultPostDetailSpringDocs
    public ReddyApiResponse<GetUserPostDetailResponseDto> getResultPostDetail(@PathVariable Long groupId) {
        GetUserPostDetailResponseDto response = resultService.getResultPostDetail(groupId);
        return ReddyApiResponse.createResponse(response, ResultMessage.RESULT_GET_SUCCESS);
    }
}
