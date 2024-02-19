package com.solution.reddy.global.exception.response;

import com.solution.reddy.global.exception.response.FieldError;
import java.util.List;

public record RunModelErrorResponse(
        int code,
        String message,
        List<FieldError> fieldErrors,
        String timestamps
) {
}
