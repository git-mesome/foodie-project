package io.wisoft.foodie.project.global.config.auth.dto;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.account.persistance.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {

    private final String oauthId;
    private final String email;
    private final String imageUrl;

    @Builder
    public UserInfoResponse(String oauthId,String email, String imageUrl) {
        this.oauthId=oauthId;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public AccountEntity toAccount(){
        return AccountEntity.builder()
                .oauthId(oauthId)
                .email(email)
                .imageUrl(imageUrl)
                .nickName("민도리")
                .role(Role.GUEST)
                .build();
    }

}
