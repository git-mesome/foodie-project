package io.wisoft.foodie.project.domain.auth.exception;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends BasicException {

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

}