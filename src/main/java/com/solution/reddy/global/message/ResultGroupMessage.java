package com.solution.reddy.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResultGroupMessage implements ResponseMessage {
    RESULT_GROUP_NOT_FOUND("게시물이 없습니다.", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;
}
