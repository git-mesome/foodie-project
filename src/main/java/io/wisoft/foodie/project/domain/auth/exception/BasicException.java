package io.wisoft.foodie.project.domain.auth.exception;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import lombok.Getter;

@Getter
public class BasicException extends RuntimeException{

    private final ErrorCode errorCode;

    public BasicException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
