package com.pinggy.assignment.config;
import com.pinggy.assignment.filter.CustomAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomAuthenticationFilter> customAuthenticationFilter() {
        FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomAuthenticationFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

