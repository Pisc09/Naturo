package com.example.Naturo.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class RdvRequest {

    @NotNull(message = "La date du rendez-vous est obligatoire")
    @FutureOrPresent(message = "La date doit être aujourd'hui ou dans le futur")
    private LocalDate date;

    @NotNull(message = "L'heure de début est obligatoire")
    private LocalTime debutHeure;

    @NotNull(message = "L'heure de fin est obligatoire")
    private LocalTime finHeure;

    private String lieu;

    private String description;

    private String link; // pour visio

    private String type; // bilan, suivi, etc.

    private String statut; // PLANIFIE, CONFIRME, ANNULE, TERMINE

    @NotNull(message = "L'ID du praticien est obligatoire")
    private Long praticienId;

    @NotNull(message = "L'ID du membre est obligatoire")
    private Long membreId;
}