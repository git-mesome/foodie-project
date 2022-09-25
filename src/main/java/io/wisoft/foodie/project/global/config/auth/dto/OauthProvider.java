package io.wisoft.foodie.project.global.config.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthProvider {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenUri;
    private final String userInfoUri;

    public OauthProvider(OauthClientProperties.User user, OauthClientProperties.Provider provider) {
        this(user.getClientId(), user.getClientSecret(), user.getRedirectUri(), provider.getTokenUri(), provider.getUserInfoUri());
    }

    @Builder
    public OauthProvider(final String clientId,
                         final String clientSecret,
                         final String redirectUri,
                         final String tokenUri,
                         final String userInfoUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenUri = tokenUri;
        this.userInfoUri = userInfoUri;
    }
}
