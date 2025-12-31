package com.example.Naturo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration générale de l'application.
 * Contient les beans utilisés dans tout le projet, notamment le PasswordEncoder.
 */
@Configuration
public class AppConfig {

    /**
     * Bean PasswordEncoder utilisé pour hasher les mots de passe
     * des utilisateurs et des admins.
     * BCrypt est l'algorithme recommandé par Spring Security.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}