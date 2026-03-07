package org.example;

// registration request
public record RegisterRequest(
        String username,
        String email,
        String password
) {}
