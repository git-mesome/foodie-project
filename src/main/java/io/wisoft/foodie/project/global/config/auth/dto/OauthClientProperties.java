package io.wisoft.foodie.project.global.config.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@ConfigurationProperties(prefix = "oauth2")
public class OauthClientProperties {

    private final Map<String,User> user = new HashMap<>();
    private final Map<String,Provider> provider = new HashMap<>();

    @Getter
    @Setter
    public static class User{

        private String clientId;
        private String clientSecret;
        private String redirectUri;

    }

    @Getter
    @Setter
    public static class Provider{

        private String tokenUri;
        private String userInfoUri;
        private String userNameAttribute;

    }

}
