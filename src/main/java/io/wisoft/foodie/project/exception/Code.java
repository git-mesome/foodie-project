package io.wisoft.foodie.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Code {
    WRONG_INPUT_CONTENT(HttpStatus.BAD_REQUEST,"203","");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    Code(final HttpStatus httpStatus,final String code,final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
