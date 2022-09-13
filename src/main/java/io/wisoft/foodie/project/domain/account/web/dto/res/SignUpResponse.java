package io.wisoft.foodie.project.domain.account.web.dto.res;

import lombok.Getter;

@Getter
public class SignUpResponse {

    private Long accountId;

    public SignUpResponse(final Long accountId) {
        this.accountId = accountId;
    }

}
