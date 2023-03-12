package io.wisoft.foodie.project.domain.auth.exception;

import io.wisoft.foodie.project.domain.auth.web.ErrorCode;

public class ChatRoomException extends BasicException {

    public ChatRoomException(ErrorCode errorCode) {
        super(errorCode);
    }

}
