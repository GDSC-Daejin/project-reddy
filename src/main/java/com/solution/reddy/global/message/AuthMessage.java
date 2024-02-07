package com.solution.reddy.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthMessage implements ResponseMessage{
    LOGIN_BAD_REQUEST("잘못된 접근입니다.", HttpStatus.BAD_REQUEST),
    INVALID_ID_TOKEN("ID TOKEN이 유효하지 않습니다", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("ACCESS TOKEN이 유효하지 않습니다", HttpStatus.FORBIDDEN),
    INVALID_USER("회원 정보를 찾을 수 없습니다.", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus status;
}
