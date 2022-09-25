package io.wisoft.foodie.project.global.config.auth;

import io.wisoft.foodie.project.global.config.auth.dto.OauthClientProperties;
import io.wisoft.foodie.project.global.config.auth.dto.OauthProvider;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class OauthAdapter {

    public static Map<String, OauthProvider> getOauthProviders(OauthClientProperties properties) {

        Map<String, OauthProvider> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key, new OauthProvider(value, properties.getProvider().get(key))));

        return oauthProvider;

    }

}
