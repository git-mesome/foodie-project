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

//    private final Map<String, List<String>> map;

    public JwtAuthInterceptor(final AuthorizationExtractor authorizationExtractor,
                              final JwtTokenProvider jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {

        final Long accountId = generateAccountIdFromRequest(request);
        request.setAttribute("id", accountId);

        if (request.getRequestURI().startsWith("/posts") && request.getMethod().equals("GET")) {
            return true;
        } else return accountId != null;    // TODO: 검증이 필요함 에러 던지기 나중에 꼭 할것!

    }

    private Long generateAccountIdFromRequest(final HttpServletRequest request) {
        final String accessToken = authorizationExtractor.extract(request, "Bearer ");
        if (accessToken.isEmpty()) {
            return null;
        } else {
            if (!jwtTokenProvider.validateToken(accessToken)) {
                throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
            }
            return jwtTokenProvider.getPayload(accessToken);
        }
    }

//    private boolean validateRequestIsAuthorized(final HttpServletRequest request) {
//        final String resourceURI = request.getRequestURI().split("/")[0];
//        return this.map.get(resourceURI).contains(request.getMethod());
//    }

}
