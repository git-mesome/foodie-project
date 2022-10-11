//package io.wisoft.foodie.project.token;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JwtConfig {
//
//    @Bean
//    public FilterRegistrationBean<JwtFilter> jwtFilter() {
//
//        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new JwtFilter());
//        //Todo 게시글 조회는 필터를 통과하지 않아야함
//        registrationBean.addUrlPatterns("/posts/*");
//        return registrationBean;
//
//    }
//}
