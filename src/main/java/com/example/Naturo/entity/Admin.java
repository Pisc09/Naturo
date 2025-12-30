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

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password; // Mot de passe hashé (BCrypt)

    private String firstname;

    private String lastname;

    @Column(name = "derniere_connexion")
    private LocalDateTime derniereConnexion;

    @Column(nullable = false)
    private boolean actif = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NiveauAdmin niveau;
}