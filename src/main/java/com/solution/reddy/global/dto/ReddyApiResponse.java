package com.solution.reddy.global.dto;

import com.solution.reddy.global.message.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReddyApiResponse<T> {
    private T data;
    private String message;
    private String code;

    public static <G> ReddyApiResponse<G> createResponse(G data, ResponseMessage responseMessage) {
        return new ReddyApiResponse<>(data, responseMessage.getMessage(), responseMessage.toString());
    }
}
