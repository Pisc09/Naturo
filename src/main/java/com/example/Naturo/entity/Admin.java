package com.example.Naturo.entity;

import com.example.Naturo.entity.enums.NiveauAdmin;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "admins")
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class Admin extends BaseEntity implements UserDetails {

    private String email;
    private String password; // Mot de passe hashé (BCrypt)
    private String firstname;
    private String lastname;
    private LocalDateTime derniereConnexion;
    private NiveauAdmin niveau;

    @Column(nullable = false)
    private boolean actif = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE-ADMIN");
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return actif;
    }
}