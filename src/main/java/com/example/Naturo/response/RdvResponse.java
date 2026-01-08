package com.example.Naturo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
public class RdvResponse {

    private Long id;

    private LocalDate date;

    private LocalTime debutHeure;

    private LocalTime finHeure;

    private String lieu;

    private String description;

    private String link;

    private String type;

    private String statut;

    private Long praticienId;

    private String praticienNom; // Optionnel : nom du praticien pour affichage

    private Long membreId;

    private String membreNom; // Optionnel : nom du membre

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}