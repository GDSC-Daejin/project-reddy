package com.solution.reddy.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CategoryMessage implements ResponseMessage{
    CATEGORY_FIND_ALL_SUCCESS("카테고리 전체 조회에 성공했습니다.", HttpStatus.OK),
    CATECORY_CREATE_SUCCESS("카테고리 등록에 성공했습니다.", HttpStatus.CREATED);

    private final String message;
    private final HttpStatus status;
}
