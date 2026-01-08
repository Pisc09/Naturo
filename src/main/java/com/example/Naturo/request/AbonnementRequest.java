package com.example.Naturo.request;

import com.example.Naturo.entity.enums.TypeAbonnement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AbonnementRequest {

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotNull(message = "Le type d'abonnement est obligatoire")
    private TypeAbonnement type;

    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    private Long userId; // Ajouté pour associer l'abonnement à l'utilisateur
}