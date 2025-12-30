package com.example.Naturo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "analyse_types")
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class AnalyseType extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String lastname; // ex. : "Ferritine", "Vitamine D", "Cortisol matin", "TSH"

    private String unite; // ex. : "ng/mL", "µg/L", "nmol/L"

    private String description;

    // Normes laboratoire (valeurs de référence classiques)
    private Double normeLaboMin;
    private Double normeLaboMax;

    // Normes optimales (selon approche fonctionnelle/naturopathie)
    private Double normeOptimaleMin;
    private Double normeOptimaleMax;

    // Optionnel : on pourra affiner plus tard par sexe, âge, etc.
    private String sexe; // "H", "F", "TOUS" ou null
}