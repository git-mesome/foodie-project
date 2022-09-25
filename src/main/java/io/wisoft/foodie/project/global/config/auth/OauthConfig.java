package io.wisoft.foodie.project.global.config.auth;

import io.wisoft.foodie.project.global.config.auth.dto.OauthClientProperties;
import io.wisoft.foodie.project.global.config.auth.dto.OauthProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthClientProperties.class)
public class OauthConfig {

    private final OauthClientProperties properties;

    public OauthConfig(OauthClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    public InMemoryProviderRepository inMemoryProviderRepository(){

        Map<String, OauthProvider> providers = OauthAdapter.getOauthProviders(properties);

        return new InMemoryProviderRepository(providers);

    }

}