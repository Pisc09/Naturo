package com.example.Naturo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AdresseResponse {

    private Long id;

    private String rue;

    private String codePostal;

    private String ville;

    private String pays;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}