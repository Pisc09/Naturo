package com.example.Naturo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "offres")
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class Offre extends BaseEntity {

    @Column(nullable = false)
    private String nom;              // ex. : "Bilan naturopathique initial"

    private String designation;      // description courte ou longue

    @Column(nullable = false)
    private String type;             // ex. : "bilan", "suivi", "atelier", "visio"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;      // prix en euros "ex. : 80.00"

    @Column(name = "duree_minutes", nullable = false)
    private Integer dureeMinutes;    // ex. : 60, 90, 30

    private String description;      // description détaillée (optionnel)

    private boolean visible = true;  // l'offre est-elle affichée publiquement ?

    // Relation avec le praticien propriétaire
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "praticien_id", nullable = false)
    private User praticien;
}
