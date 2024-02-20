package com.solution.reddy.global.exception;

import com.solution.reddy.global.exception.response.RunModelErrorResponse;
import com.solution.reddy.global.message.ResponseMessage;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class RunModelFeignException extends ResponseStatusException {
    private final ResponseMessage responseMessage;
    private final RunModelErrorResponse errorResponse;

    public RunModelFeignException(ResponseMessage apiResponseType) {
        super(apiResponseType.getStatus(), apiResponseType.getMessage());
        this.responseMessage = apiResponseType;
        this.errorResponse = null;
    }

    public RunModelFeignException(ResponseMessage apiResponseType, RunModelErrorResponse errorResponse) {
        super(apiResponseType.getStatus(), apiResponseType.getMessage());
        this.responseMessage = apiResponseType;
        this.errorResponse = errorResponse;
    }
}