package com.hotel.book.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception used for JWT validation failures so that
 * Spring Security can route them to the AuthenticationEntryPoint (401).
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}

