package com.example.Naturo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String email;

    private String firstname;

    private String lastname;

    private String phone;

    private LocalDate dateOfBirth;

    private String gender;

    private boolean enable;

    private Set<String> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Infos spécifiques si praticien ou membre (optionnel, tu peux les ajouter via embeddables si besoin)
    private String siret; // uniquement si praticien
    private String specialites;
    private String allergies; // uniquement si membre
    private String objectifs;
}