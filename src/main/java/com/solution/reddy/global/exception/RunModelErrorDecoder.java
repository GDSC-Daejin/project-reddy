package com.solution.reddy.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.reddy.global.exception.response.RunModelErrorResponse;
import com.solution.reddy.global.message.FeignMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import lombok.SneakyThrows;

public class RunModelErrorDecoder implements ErrorDecoder {
    private final StringDecoder stringDecoder = new StringDecoder();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public RunModelFeignException decode(String methodKey, Response response) {

        String message = stringDecoder.decode(response, String.class).toString();
        RunModelErrorResponse errorResponse = objectMapper.readValue(message, RunModelErrorResponse.class);

        return new RunModelFeignException(FeignMessage.CONNECT_FAIL);
    }
}