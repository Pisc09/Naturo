package com.example.Naturo.dto;

public record AuthResponse(
        String token,
        String type,
        String role
) {
    // Constructeur pratique avec type par défaut "Bearer"
    public AuthResponse(String token, String role) {
        this(token, "Bearer", role);
    }
}