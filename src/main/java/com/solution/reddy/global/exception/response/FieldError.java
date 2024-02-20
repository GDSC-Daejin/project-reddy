package com.solution.reddy.global.exception.response;

public record FieldError(
        String field,
        String value,
        String message
) {
}
