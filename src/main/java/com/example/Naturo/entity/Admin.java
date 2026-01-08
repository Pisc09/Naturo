package com.example.Naturo.entity;

import com.example.Naturo.entity.enums.NiveauAdmin;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class Admin extends BaseEntity {

    private String email;
    private String password; // Mot de passe hashé (BCrypt)
    private String firstname;
    private String lastname;
    private LocalDateTime derniereConnexion;
    private NiveauAdmin niveau;

    @Column(nullable = false)
    private boolean actif = true;
}