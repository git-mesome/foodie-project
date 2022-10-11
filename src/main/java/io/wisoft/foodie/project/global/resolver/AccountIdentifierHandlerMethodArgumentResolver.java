package io.wisoft.foodie.project.global.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class AccountIdentifierHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        final boolean isAccountIdentifierAnnotation = parameter.hasParameterAnnotation(AccountIdentifier.class);
        final boolean isLongParameter = parameter.getParameterType() == Long.class;
        return isAccountIdentifierAnnotation && isLongParameter;
    }

    @Override
    public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest)  webRequest.getNativeRequest();
        return (Long)request.getAttribute("id");

    }

}
