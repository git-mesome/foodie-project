package io.wisoft.foodie.project.global.config.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OauthTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    private String scope;

    @JsonProperty("token_type")
    private String tokenType;

    @Builder
    public OauthTokenResponse(final String accessToken,
                              final String scope,
                              final String tokenType) {
        this.accessToken = accessToken;
        this.scope = scope;
        this.tokenType = tokenType;
    }

}
