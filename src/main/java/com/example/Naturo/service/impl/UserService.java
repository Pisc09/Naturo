package com.example.Naturo.service.impl;

import com.example.Naturo.entity.User;
import com.example.Naturo.entity.enums.TypeAbonnement;
import com.example.Naturo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Récupérer tous les utilisateurs
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Trouver par ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Trouver par email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Création d'un nouvel utilisateur (inscription)
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Tu peux ajouter des rôles par défaut ici si besoin
        return userRepository.save(user);
    }

    // Mise à jour
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Suppression
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Vérifier si un utilisateur a un abonnement actif du bon type
    public boolean hasAbonnementActif(User user, TypeAbonnement typeRequis) {
        if (user.getAbonnements() == null) return false;

        return user.getAbonnements().stream()
                .anyMatch(ab -> ab.isActif()
                        && ab.getType().equals(typeRequis)
                        && (ab.getDateFin() == null || ab.getDateFin().isAfter(LocalDate.now())));
    }
}