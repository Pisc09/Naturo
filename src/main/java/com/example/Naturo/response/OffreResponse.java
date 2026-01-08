package com.example.Naturo.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OffreResponse {

    private Long id;

    private String nom;

    private String designation;

    private String type;

    private BigDecimal montant;

    private Integer dureeMinutes;

    private String description;

    private boolean visible;

    private Long praticienId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}