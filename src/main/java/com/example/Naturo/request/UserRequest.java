package com.example.Naturo.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {

    @Column(unique = true, nullable = false)
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial"
    )
    private String password;

    private String firstname;

    private String lastname;

    private String phone;

    private LocalDate dateOfBirth;

    private String gender; // "M", "F", "AUTRE"

    // Pour les rôles (ex. : inscription en tant que praticien)
    private String role; // "MEMBRE" ou "PRATICIEN" (on gérera les rôles via Set plus tard si besoin)
}