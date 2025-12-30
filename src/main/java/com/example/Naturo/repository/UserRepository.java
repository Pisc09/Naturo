package com.example.Naturo.repository;

import com.example.Naturo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // Ajouter des méthodes personnalisées ici plus tard si besoin
}