package io.wisoft.foodie.project.domain.account.web.dto.res;


import lombok.Getter;

@Getter
public class SignUpResponse {

    private final Long accountId;
    private final String nickname;
    private final String imageUrl;
    private String accessToken;
    private String refreshToken;

    public SignUpResponse(final Long accountId,
                          final String nickname,
                          final String imageUrl) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }

    public void setToken(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
