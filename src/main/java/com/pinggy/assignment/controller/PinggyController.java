package com.pinggy.assignment.controller;

import com.pinggy.assignment.filter.CustomAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PinggyController {

    @GetMapping("/")
    public String getHeaderValue() {
        String authHeaderValue = CustomAuthenticationFilter.getAuthHeaderValue();
        CustomAuthenticationFilter.clearAuthHeaderValue();
        return authHeaderValue;
    }
}
