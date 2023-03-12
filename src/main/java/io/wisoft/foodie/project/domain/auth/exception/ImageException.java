package io.wisoft.foodie.project.domain.auth.exception;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;

public class ImageException extends BasicException {

    public ImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
