package io.wisoft.foodie.project.domain.account.web.dto.res;

import lombok.Getter;

@Getter
public class FindAccountResponse {

    private String email;
    private String nickName;


    public FindAccountResponse(final String email, final String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

}