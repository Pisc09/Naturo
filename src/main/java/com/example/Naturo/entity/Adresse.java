package com.example.Naturo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "adresses")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder
public class Adresse extends BaseEntity {

    private String rue;
    private String codePostal;
    private String ville;
    private String pays;
}
