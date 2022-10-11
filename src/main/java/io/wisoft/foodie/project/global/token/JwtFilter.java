//package io.wisoft.foodie.project.token;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//public class JwtFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String authHeader = request.getHeader("Authorization");
//        String bearerString = "Bearer ";
//
//        if (!StringUtils.hasLength(authHeader) || !authHeader.startsWith((bearerString))) {
//            throw new ServletException(new IllegalStateException("무효 토큰"));
//        } else {
//            String accessToken = authHeader.substring(bearerString.length());
//            try {
////                Jws<Claims> claims = Jwts.parserBuilder()
////                        .setSigningKey(secretKey)
////                        .build()
////                        .parseClaimsJws(accessToken)
////                        .getBody();
////                request.setAttribute("claims", claims);
//            } catch (JwtException | IllegalArgumentException e) {
//
//            }
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//
//    }
//
//}
