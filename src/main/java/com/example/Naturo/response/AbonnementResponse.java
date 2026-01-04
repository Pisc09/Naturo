package com.example.Naturo.response;

import com.example.Naturo.entity.enums.TypeAbonnement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AbonnementResponse {
    private BigDecimal montant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDateTime createdAt;
    private TypeAbonnement type;
}
