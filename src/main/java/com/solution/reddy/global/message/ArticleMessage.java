package com.solution.reddy.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ArticleMessage implements ResponseMessage{
    ARTICLE_NOT_FOUD("기사를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    GET_ARTICLE_DETAIL_SUCCESS("기사 상세조회를 성공했습니다.", HttpStatus.OK),
    GET_ARTICLE_TITLE_SUCCESS("기사 타이틀 조회를 성공했습니다.", HttpStatus.OK),
    ARTICLE_IS_EMPTY("기사가 없습니다.", HttpStatus.NO_CONTENT),
    ARTICLE_POST_SUCCESS("기사 등록에 성공했습니다.", HttpStatus.CREATED),
    ARTICLE_POST_FAIL("기사 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ARTICLE_EMOTION_POST_SUCCESS("이모지 누르기에 성공했습니다.", HttpStatus.OK),
    EMOTION_NOT_FOUND("이모지를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CLICK_EMOTION_FAIL("이모지 누르기에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    GET_TODAYS_ARTICLE_TITLE_SUCCESS("오늘의 기사 타이틀 조회를 성공했습니다.", HttpStatus.OK);

    private final String message;
    private final HttpStatus status;
}
