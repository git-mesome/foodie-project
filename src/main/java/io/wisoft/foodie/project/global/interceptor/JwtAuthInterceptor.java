package io.wisoft.foodie.project.global.interceptor;

import io.wisoft.foodie.project.global.token.AuthorizationExtractor;
import io.wisoft.foodie.project.global.token.JwtTokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthInterceptor(final AuthorizationExtractor authorizationExtractor,
                              final JwtTokenProvider jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {

        if (request.getMethod().equals("GET")) {
            return true;
        }

        final String accessToken = authorizationExtractor.extract(request, "Bearer");

        if (accessToken.isEmpty()) {
            throw new IllegalArgumentException("토큰이 비었습니다.");
        }

        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        final Long id = jwtTokenProvider.getPayload(accessToken);
        request.setAttribute("id", id);

        return true;

    }

}
