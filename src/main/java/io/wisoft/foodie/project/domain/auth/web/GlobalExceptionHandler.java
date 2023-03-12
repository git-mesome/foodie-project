package io.wisoft.foodie.project.domain.auth.web;

import io.jsonwebtoken.JwtException;
import io.wisoft.foodie.project.domain.auth.exception.*;
import io.wisoft.foodie.project.domain.auth.web.dto.res.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    private ResponseEntity<ErrorResponse> buildErrorResponse(ErrorCode errorCode){
        return new ResponseEntity<>(new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getMessage()),
                errorCode.getHttpStatus());
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(AccountException e){

        return buildErrorResponse(e.getErrorCode());

    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorResponse> handlePostException(PostException e){

        return buildErrorResponse(e.getErrorCode());

    }

    @ExceptionHandler(ChatRoomException.class)
    public ResponseEntity<ErrorResponse> handleChatRoomException(ChatRoomException e){

        return buildErrorResponse(e.getErrorCode());

    }

    @ExceptionHandler(ImageException.class)
    public ResponseEntity<ErrorResponse> handleImageException(ImageException e){

        return buildErrorResponse(e.getErrorCode());

    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e){

       return buildErrorResponse(e.getErrorCode());

    }

    // 그 외의 JWT 예외 처리
    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<String> handleJwtException(JwtException e) {
        String message = "JWT exception: " + e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    // 그 외의 예외 처리
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String message = "Unexpected exception: " + e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
