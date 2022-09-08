package io.wisoft.foodie.project.web.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class SignInResponse {

    private Long accountId;

    public SignInResponse(final Long accountId) {
        this.accountId = accountId;
    }
}
