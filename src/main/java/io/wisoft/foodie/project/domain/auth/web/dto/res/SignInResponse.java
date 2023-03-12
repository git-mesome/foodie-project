package io.wisoft.foodie.project.domain.auth.web.dto.res;


import lombok.Getter;

@Getter
public class SignInResponse {

    private final Long accountId;
    private final String nickname;
    private final String profileImagePath;
    private String accessToken;
    private String refreshToken;

    public SignInResponse(final Long accountId,
                          final String nickname,
                          final String profileImagePath) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.profileImagePath = profileImagePath;
    }

    public void setToken(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
