package com.example.Naturo.controller;

import com.example.Naturo.entity.Abonnement;
import com.example.Naturo.service.impl.AbonnementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/abonnements")
@RequiredArgsConstructor
public class AbonnementController {

    private final AbonnementService abonnementService;

    // Tous les abonnements "Admin"
    @GetMapping
    public ResponseEntity<List<Abonnement>> getAll() {
        return ResponseEntity.ok(abonnementService.findAll());
    }

    // Abonnements d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Abonnement>> getByUser(@PathVariable Long userId) {
        List<Abonnement> abonnements = abonnementService.findByUserId(userId);
        return ResponseEntity.ok(abonnements);
    }

    // Souscription à un nouvel abonnement
    @PostMapping
    public ResponseEntity<Abonnement> createAbonnement(@Valid @RequestBody Abonnement abonnement) {
        Abonnement created = abonnementService.createAbonnement(abonnement);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    // Désactivation (expiration ou annulation)
    @PatchMapping("/{id}/desactiver")
    public ResponseEntity<Void> desactiver(@PathVariable Long id) {
        abonnementService.desactiverAbonnement(id);
        return ResponseEntity.noContent().build();
    }
}