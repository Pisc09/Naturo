package com.example.Naturo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyseTypeRequest {

    @NotBlank(message = "Le nom de l'analyse est obligatoire")
    private String nom;

    private String unite;

    private String description;

    private Double normeLaboMin;

    private Double normeLaboMax;

    private Double normeOptimaleMin;

    private Double normeOptimaleMax;

    private String sexe; // "H", "F", "TOUS" ou null
}