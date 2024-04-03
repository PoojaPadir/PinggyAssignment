package com.pinggy.assignment.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private static final ThreadLocal<String> AUTH_HEADER = new ThreadLocal<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeaderValue = request.getHeader("PinggyAuthHeader");

            if (authHeaderValue != null && !authHeaderValue.isEmpty()) {
                AUTH_HEADER.set(authHeaderValue);
                filterChain.doFilter(request, response);
            } else {
                sendUnauthorizedResponse(response, request.getRequestURI());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String requestUri) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("timestamp", OffsetDateTime.now().toString());
        errorResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorResponse.put("error", "Unauthorized");
        errorResponse.put("path", requestUri);

        String errorMessage = objectMapper.writeValueAsString(errorResponse);

        PrintWriter out = response.getWriter();
        out.print(errorMessage);
        out.flush();
    }

    public static String getAuthHeaderValue() {
        return AUTH_HEADER.get();
    }

    public static void clearAuthHeaderValue() {
        AUTH_HEADER.remove();
    }
}
