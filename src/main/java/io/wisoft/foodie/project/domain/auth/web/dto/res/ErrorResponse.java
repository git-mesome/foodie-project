package io.wisoft.foodie.project.domain.auth.web.dto.res;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int httpStatus;
    private final String errorMessage;

    public ErrorResponse(int httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
