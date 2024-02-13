package com.solution.reddy.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ArticleMessage implements ResponseMessage{
    ARTICLE_NOT_FOUD("기사를 찾을 수 없습니다.", HttpStatus.FORBIDDEN),
    GET_ARTICLE_DETAIL_SUCCESS("기사 상세조회를 성공했습니다.", HttpStatus.OK),
    GET_ARTICLE_TITLE_SUCCESS("기사 타이틀 조회를 성공했습니다.", HttpStatus.OK);

    private final String message;
    private final HttpStatus status;
}
