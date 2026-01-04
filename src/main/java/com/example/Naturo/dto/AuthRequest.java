package com.example.Naturo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @Email(message = "Email invalide")
        @NotBlank(message = "Email requis")
        String email,

        @NotBlank(message = "Mot de passe requis")
        String password
) {}