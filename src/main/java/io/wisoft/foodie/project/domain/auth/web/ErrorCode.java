package io.wisoft.foodie.project.domain.auth.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NOT_FOUND_ACCOUNT("A001", "Account not found", HttpStatus.NOT_FOUND),
    NOT_FOUND_CHAT_ROOM("CR01", "ChatRoom not found", HttpStatus.NOT_FOUND),
    NOT_FOUND_POST("P001", "Post not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED_POST_EDIT("P002", "Unauthorized post edit", HttpStatus.BAD_REQUEST),

    UNSUPPORTED_IMAGE_FORMAT("I002", "Unsupported image file format", HttpStatus.BAD_REQUEST),
    IMAGE_UPLOAD_ERROR("I003", "Image upload error", HttpStatus.BAD_REQUEST),
    INVALID_URL_FORMAT("I005", "Invalid URL format", HttpStatus.INTERNAL_SERVER_ERROR),

    //token
    INVALID_TOKEN("T001", "Token ", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("T002", "Token is expired", HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN("T003", "Token is unsupported", HttpStatus.UNAUTHORIZED);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
