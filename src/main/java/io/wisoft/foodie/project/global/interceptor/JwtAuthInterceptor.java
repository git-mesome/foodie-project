package io.wisoft.foodie.project.global.interceptor;

import io.wisoft.foodie.project.domain.auth.exception.AccountException;
import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import io.wisoft.foodie.project.domain.auth.exception.InvalidTokenException;
import io.wisoft.foodie.project.global.token.AuthorizationExtractor;
import io.wisoft.foodie.project.global.token.JwtTokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

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
                             final Object handler) throws Exception {

        //OPTIONS 요청 허용
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        final Long accountId = generateAccountIdFromRequest(request);
        request.setAttribute("id", accountId);

        if (isGetRequestForPosts(request)) {
            return true;
        }

        if (accountId == null) {
            throw new AccountException(ErrorCode.NOT_FOUND_ACCOUNT);
        }

        throw new AccessDeniedException("Access is denied");

    }

    private boolean isGetRequestForPosts(final HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/posts") && request.getMethod().equals("GET");
    }

    private Long generateAccountIdFromRequest(final HttpServletRequest request) {
        final String accessToken = authorizationExtractor.extract(request, "Bearer ");
        if (accessToken == null || accessToken.isEmpty()) {
            return null;
        } else {
            if (!jwtTokenProvider.validateToken(accessToken)) {
                throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
            }
            return jwtTokenProvider.getPayload(accessToken);
        }
    }

//    private boolean validateRequestIsAuthorized(final HttpServletRequest request) {
//        final String resourceURI = request.getRequestURI().split("/")[0];
//        return this.map.get(resourceURI).contains(request.getMethod());
//    }

}
