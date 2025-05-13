package com.sopt.DaisoMall.global.common.exception;

import com.sopt.DaisoMall.global.common.exception.response.ExceptionResponse;
import com.sopt.DaisoMall.global.common.exception.response.ValidErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleException(BaseException e) {
        ExceptionResponse body = ExceptionResponse.of(e.getStatus(), e.getMessage());

        return new ResponseEntity<>(body, e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidErrorResponse>> handleValidation(MethodArgumentNotValidException e) {
        List<ValidErrorResponse> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> ValidErrorResponse.of(
                        fe.getField(),
                        fe.getDefaultMessage(),
                        fe.getRejectedValue()
                ))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException e) {
        ExceptionResponse body = ExceptionResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResourceFound(NoResourceFoundException e) {
        ExceptionResponse body = ExceptionResponse.of(HttpStatus.NOT_FOUND, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        ExceptionResponse body = ExceptionResponse.of(
                HttpStatus.METHOD_NOT_ALLOWED,
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof BaseException baseEx) {
            HttpStatus status = baseEx.getStatus();
            ExceptionResponse body = ExceptionResponse.of(status, baseEx.getMessage());

            return ResponseEntity.status(status).body(body);
        }

        ExceptionResponse body = ExceptionResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                ErrorMessage.JSON_PARSE_ERROR.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAll(Exception e) {
        ExceptionResponse body = ExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
