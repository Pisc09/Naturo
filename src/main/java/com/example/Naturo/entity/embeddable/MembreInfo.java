package com.example.Naturo.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class MembreInfo {

    private String allergies;
    private String complements;
    private String objectifs;
}
