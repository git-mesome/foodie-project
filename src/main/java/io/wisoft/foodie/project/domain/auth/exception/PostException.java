package io.wisoft.foodie.project.domain.auth.exception;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import lombok.Getter;;

@Getter
public class PostException extends BasicException {

    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

}
