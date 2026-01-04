package com.example.Naturo.controller;

import com.example.Naturo.entity.Offre;
import com.example.Naturo.entity.User;
import com.example.Naturo.service.impl.OffreService;
import com.example.Naturo.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreService offreService;
    private final UserService userService; // Pour vérifier l'existence du praticien

    /**
     * Catalog public : toutes les offres visibles par les membres
     */
    @GetMapping("/public")
    public ResponseEntity<List<Offre>> getOffresPubliques() {
        List<Offre> offres = offreService.findOffresVisibles();
        return ResponseEntity.ok(offres);
    }

    /**
     * Offres appartenant à un praticien spécifique (pour son dashboard)
     */
    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<Offre>> getOffresByPraticien(@PathVariable Long praticienId) {
        // Vérification que le praticien existe (sécurité + UX)
        User praticien = userService.findById(praticienId)
                .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé avec l'ID : " + praticienId));

        List<Offre> offres = offreService.findByPraticien(praticien);
        return ResponseEntity.ok(offres);
    }

    /**
     * Création d'une nouvelle offre (réservé au praticien propriétaire)
     */
    @PostMapping
    public ResponseEntity<Offre> createOffre(@Valid @RequestBody Offre offre) {
        Offre created = offreService.createOffre(offre);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    /**
     * Mise à jour d'une offre existante
     */
    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable Long id, @Valid @RequestBody Offre offre) {
        // Vérification que l'offre existe
        if (!offreService.findAll().stream().anyMatch(o -> o.getId().equals(id))) {
            return ResponseEntity.notFound().build();
        }
        offre.setId(id);
        Offre updated = offreService.updateOffre(offre);
        return ResponseEntity.ok(updated);
    }

    /**
     * Suppression d'une offre
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        // Vérification existence avant suppression
        if (!offreService.findAll().stream().anyMatch(o -> o.getId().equals(id))) {
            return ResponseEntity.notFound().build();
        }
        offreService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Activer/désactiver la visibilité d'une offre (pratique pour le praticien)
     */
    @PatchMapping("/{id}/visibilite")
    public ResponseEntity<Void> toggleVisibilite(@PathVariable Long id) {
        offreService.toggleVisibilite(id);
        return ResponseEntity.noContent().build();
    }
}