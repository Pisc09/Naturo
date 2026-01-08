package com.example.Naturo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnalyseTypeResponse {

    private Long id;

    private String nom; // ou firstname ?

    private String unite;

    private String description;

    private Double normeLaboMin;

    private Double normeLaboMax;

    private Double normeOptimaleMin;

    private Double normeOptimaleMax;

    private String sexe;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}