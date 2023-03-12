package io.wisoft.foodie.project.domain.auth.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    ACCOUNT_NOT_FOUND("A001","Account not found", HttpStatus.NOT_FOUND),

    //token
    INVALID_TOKEN("T001","Token ",HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("T002","Token is expired",HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN("T003","Token ",HttpStatus.UNAUTHORIZED);



    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
