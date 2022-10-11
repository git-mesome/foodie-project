package io.wisoft.foodie.project.global.config;

import io.wisoft.foodie.project.global.interceptor.JwtAuthInterceptor;
import io.wisoft.foodie.project.global.resolver.AccountIdentifier;
import io.wisoft.foodie.project.global.resolver.AccountIdentifierHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //CORS
    private final long MAX_AGE_SECS = 3600;
    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebMvcConfig(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //HandlerInterceptor 객체를 설정, 로그인 상태인지 확인되면 기능과 연결된 url 등록
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/posts/**")
                .addPathPatterns("/auth/test/**")
                .excludePathPatterns("posts/find");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add((handlerMethodArgumentResolver()));
    }

    @Bean
    public AccountIdentifierHandlerMethodArgumentResolver handlerMethodArgumentResolver(){
        return new AccountIdentifierHandlerMethodArgumentResolver();
    }


}
