package com.example.Naturo.entity;

import com.example.Naturo.entity.embeddable.MembreInfo;
import com.example.Naturo.entity.embeddable.PraticienInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

    // Champs communs
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private Date dateOfBirth;
    private String gender;
    private boolean enable;
    private String provider;
    private String role;

    // Rôles de sécurité
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>(); // "MEMBRE", "PRATICIEN"

    // Infos spécifiques membre "null si pas membre"
    @Embedded
    private MembreInfo membreInfo;

    // Infos spécifiques praticien "null si pas praticien"
    @Embedded
    private PraticienInfo praticienInfo;

    // Adresse de l'utilisateur
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    // Abonnements de cet utilisateur "un ou plusieurs historiques"
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Abonnement> abonnements = new ArrayList<>();

    // RDV pris "si membre"
    @OneToMany(mappedBy = "membre")
    private List<Rdv> rdvPris = new ArrayList<>();

    // RDV animés "si praticien"
    @OneToMany(mappedBy = "praticien")
    private List<Rdv> rdvAnimes = new ArrayList<>();

    // Résultats que le praticien a saisis pour ses membres
    @OneToMany(mappedBy = "praticien")
    private List<Resultat> resultatsSaisis = new ArrayList<>();

    // Résultats concernant ce membre (ses analyses)
    @OneToMany(mappedBy = "membre")
    private List<Resultat> resultatsPersonnels = new ArrayList<>();

    // Méthode utilitaire pratique
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
        if (adresse != null) {
            // Optionnel : pour maintenir la cohérence si on veut bidirectionnel plus tard
        }
    }

    // Méthodes utilitaires
    public void addAbonnement(Abonnement abonnement) {
        abonnements.add(abonnement);
        abonnement.setUser(this);
    }

    public void addRdvAnime(Rdv rdv) {
        rdvAnimes.add(rdv);
        rdv.setPraticien(this);
    }

    public void addRdvPris(Rdv rdv) {
        rdvPris.add(rdv);
        rdv.setMembre(this);
    }
}