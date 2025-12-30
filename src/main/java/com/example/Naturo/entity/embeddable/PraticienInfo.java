package com.example.Naturo.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class PraticienInfo {

    private String siret;
    private String iban;
    private String diplomes;
    private String specialites;
}
