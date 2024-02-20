package com.solution.reddy.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResultMessage implements ResponseMessage{
    RESULT_NOT_FOUND("게시물이 없습니다.", HttpStatus.NOT_FOUND),
    RESULT_SAVE_SUCCESS("게시물 저장에 성공했습니다.", HttpStatus.CREATED),
    RESULT_GET_SUCCESS("결과 불러오기에 성공했습니다.", HttpStatus.OK);

    private final String message;
    private final HttpStatus status;
}
