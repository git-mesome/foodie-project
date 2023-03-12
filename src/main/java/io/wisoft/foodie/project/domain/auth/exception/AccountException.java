package io.wisoft.foodie.project.domain.auth.exception;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import lombok.Getter;

@Getter
public class AccountException extends BasicException {

    public AccountException(ErrorCode errorCode) {
        super(errorCode);
    }

}
