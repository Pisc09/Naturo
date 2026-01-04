package com.example.Naturo.request;

import com.example.Naturo.entity.enums.TypeAbonnement;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AbonnementRequest {
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;
    @Column(name = "date_fin") // null = reconduction tacite
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAbonnement type;
}
