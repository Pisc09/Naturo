package com.example.Naturo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "rendezvous")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder
public class Rdv extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "debut_heure", nullable = false)
    private LocalTime debutHeure;

    @Column(name = "fin_heure", nullable = false)
    private LocalTime finHeure;

    private String lieu;

    private String description;

    private String link;  // lien visio

    private String type;  // bilan, suivi, etc.

    // Praticien qui anime la consultation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "praticien_id", nullable = false)
    private User praticien;

    // Membre qui prend le rendez-vous
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_id", nullable = false)
    private User membre;

    private String statut; // PLANIFIE, CONFIRME, ANNULE, TERMINE, etc.
}