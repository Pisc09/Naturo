package com.example.Naturo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "resultats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Resultat extends BaseEntity {

    @Column(nullable = false)
    private LocalDate dateSaisie; // date à laquelle le praticien entre le résultat

    @Column(nullable = false)
    private Double valeur; // valeur numérique saisie (ex. : 45.6)

    private String commentaire; // commentaire libre du praticien

    // Type d'analyse (lié aux normes)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_type_id", nullable = false)
    private AnalyseType analyseType;

    // Le membre concerné
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_id", nullable = false)
    private User membre;

    // Le praticien qui a saisi le résultat
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "praticien_id", nullable = false)
    private User praticien;
}