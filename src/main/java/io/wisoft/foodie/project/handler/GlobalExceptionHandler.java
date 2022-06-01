package io.wisoft.foodie.project.handler;

import io.wisoft.foodie.project.handler.exception.AccountNotFoundException;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> accountNotFound(final AccountNotFoundException e) {
        final ErrorResponse errorResponse =
                generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalState(final IllegalStateException e) {
        final ErrorResponse errorResponse =
                generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity
                .internalServerError()
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgument(final IllegalArgumentException e) {
        final ErrorResponse errorResponse =
                generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ErrorResponse> methodArgumentNotValid(final BindException e) {
        final List<String> errorMessages = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(errorMessages));
    }

    private ErrorResponse generateErrorResponseWithMessage(final String errorMessage) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.add(errorMessage);

        return errorResponse;
    }

}

@Getter
class ErrorResponse {
    final List<String> messages;

    ErrorResponse() {
        this(new ArrayList<>());
    }

    ErrorResponse(final List<String> messages) {
        this.messages = messages;
    }

    void add(final String message) {
        this.messages.add(message);
    }
}