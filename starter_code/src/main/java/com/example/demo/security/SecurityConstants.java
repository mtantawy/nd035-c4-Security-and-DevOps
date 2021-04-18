package com.example.demo.security;

public class SecurityConstants {

    // IRL this should not be committed, instead, injected at runtime
    public static final String SECRET = "F3QnD79qg3DWhVB";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user/create";
    public static final int MIN_PASSWORD_LENGTH = 7;
}