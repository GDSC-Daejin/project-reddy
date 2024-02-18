package com.solution.reddy.global.exception;

import static com.solution.reddy.global.message.DefaultMessage.*;

import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.message.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        return makeErrorResponse(e.getResponseMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleArgumentTypeMismatch(final MethodArgumentTypeMismatchException e) {
        return makeErrorResponse(BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(final IllegalArgumentException e) {
        return makeErrorResponse(INTERNAL_SERVER_ERROR);
    }

    // Request Body 형식이 맞지 않거나, JSON 형태를 지키지 않았을 경우 발생.
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return makeErrorResponse(INVALID_JSON);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException e,
            final HttpHeaders headers,
            final HttpStatusCode status,
            final WebRequest request
    ) {
        return makeErrorResponse(INVALID_PARAMETER);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(final Exception ex) {
        System.out.println(ex.getMessage());
        return makeErrorResponse(INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> makeErrorResponse(ResponseMessage errorMessage) {
        ReddyApiResponse<?> errorResponse = ReddyApiResponse.createResponse(null, errorMessage);
        return ResponseEntity.status(errorMessage.getStatus()).body(errorResponse);
    }
}
