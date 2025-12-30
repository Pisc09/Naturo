package com.example.Naturo.entity;

import com.example.Naturo.entity.enums.TypeAbonnement;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

@Table(name = "abonnements")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder
public class Abonnement extends BaseEntity {

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;  // 7.99 ou 20.00

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin") // null = reconduction tacite
    private LocalDate dateFin;

    @Column(nullable = false)
    private boolean actif = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAbonnement type;

    // L'utilisateur propriétaire de cet abonnement
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}