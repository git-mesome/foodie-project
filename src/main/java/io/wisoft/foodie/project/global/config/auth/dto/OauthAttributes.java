package io.wisoft.foodie.project.global.config.auth.dto;

import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {

    NAVER("naver") {
        @Override
        public UserInfoResponse of(Map<String, Object> attributes) {
            Map<String,Object> response = (Map<String,Object>) attributes.get("response");
            return UserInfoResponse.builder()
                    .oauthId((String)response.get("id"))
                    .email((String) response.get("email"))
                    .imageUrl((String) response.get("profile_image"))
                    .build();
        }

    },
    GOOGLE("google") {
        @Override
        public UserInfoResponse of(Map<String, Object> attributes) {
            return UserInfoResponse.builder()
                    .oauthId(String.valueOf(attributes.get("sub")))
                    .email((String) attributes.get("email"))
                    .imageUrl((String) attributes.get("picture"))
                    .build();
        }

    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static UserInfoResponse extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract UserInfoResponse of(Map<String,Object> attributes);

}
