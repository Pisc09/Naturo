package com.example.Naturo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdresseRequest {

    @NotBlank(message = "La rue est obligatoire")
    private String rue;

    @NotBlank(message = "Le code postal est obligatoire")
    private String codePostal;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    private String pays; // Optionnel (par défaut "France" si tu veux)
}