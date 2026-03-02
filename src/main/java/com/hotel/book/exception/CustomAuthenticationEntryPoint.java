package com.hotel.book.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.book.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        int status = HttpServletResponse.SC_UNAUTHORIZED;
        MDC.put("status", String.valueOf(status));

        response.setStatus(status);
        response.setContentType("application/json");

        String path = request.getRequestURI();
        String authHeader = request.getHeader("Authorization");

        String message;

        // Login failures (wrong email/password)
        if (path != null && path.startsWith("/api/auth/login")) {
            message = "Invalid email or password";

        // JWT-specific failures raised from JwtUtil
        } else if (authException.getClass().getSimpleName().equals("JwtAuthenticationException")) {
            String exMsg = authException.getMessage() != null ? authException.getMessage() : "";
            if (exMsg.toLowerCase().contains("expired")) {
                message = "JWT token expired";
            } else {
                message = "Invalid JWT token";
            }

        // Missing token when accessing a protected endpoint
        } else if (authHeader == null || authHeader.isBlank()) {
            message = "Missing JWT token. Please include a valid Bearer token in the Authorization header.";

        // Fallback for any other auth issue
        } else if (authException instanceof BadCredentialsException) {
            message = "Invalid credentials";
        } else {
            message = "Authentication failed";
        }

        log.warn("Authentication failure on {}: {}", path, authException.getMessage());

        ErrorResponse body = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status)
                .error("Unauthorized")
                .message(message)
                .path(request.getRequestURI())
                .build();

        objectMapper.writeValue(response.getOutputStream(), body);
    }
}