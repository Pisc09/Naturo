package com.example.Naturo.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ResultatRequest {

    @NotNull(message = "La date de saisie est obligatoire")
    private LocalDate dateSaisie;

    @NotNull(message = "La valeur est obligatoire")
    @Positive(message = "La valeur doit être positive")
    private Double valeur;

    private String commentaire;

    @NotNull(message = "L'ID du type d'analyse est obligatoire")
    private Long analyseTypeId;

    @NotNull(message = "L'ID du membre est obligatoire")
    private Long membreId;

    @NotNull(message = "L'ID du praticien est obligatoire")
    private Long praticienId;
}