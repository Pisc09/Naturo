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

    private BigDecimal montant;  // 7.99 ou 20.00
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeAbonnement type;

    @Column(nullable = false)
    private boolean actif = true;


    // L'utilisateur propriétaire de cet abonnement
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}