package io.wisoft.foodie.project.exception;

import lombok.Getter;

@Getter
public class PrivateException extends RuntimeException {

    private Code code;

    public PrivateException(Code code) {

        super(code.getMessage());
        this.code = code;

    }

}
