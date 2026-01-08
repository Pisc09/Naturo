package com.example.Naturo.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OffreRequest {

    @NotBlank(message = "Le nom de l'offre est obligatoire")
    private String nom;

    private String designation;

    @NotBlank(message = "Le type d'offre est obligatoire")
    private String type;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    @Min(value = 1, message = "La durée doit être au moins 1 minute")
    private Integer dureeMinutes;

    private String description;

    private Boolean visible;

    @NotNull(message = "L'ID du praticien est obligatoire")
    private Long praticienId;
}