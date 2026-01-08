package com.example.Naturo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class ResultatResponse {

    private Long id;

    private LocalDate dateSaisie;

    private Double valeur;

    private String commentaire;

    private Long analyseTypeId;

    private String analyseTypeNom; // Pour affichage

    private String unite; // Ex. : ng/mL

    private Long membreId;

    private String membreNom;

    private Long praticienId;

    private String praticienNom;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}