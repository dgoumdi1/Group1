package org.example;

// login request
public record LoginRequest(
        String usernameOrEmail,
        String password
) {}
