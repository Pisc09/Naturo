package com.example.Naturo.controller;

import com.example.Naturo.entity.Resultat;
import com.example.Naturo.service.impl.ResultatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/resultats")
@RequiredArgsConstructor
public class ResultatController {

    private final ResultatService resultatService;

    // Tous les résultats (admin)
    @GetMapping
    public ResponseEntity<List<Resultat>> getAllResultats() {
        return ResponseEntity.ok(resultatService.findAll());
    }

    // Résultats saisis par un praticien
    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<Resultat>> getByPraticien(@PathVariable Long praticienId) {
        List<Resultat> resultats = resultatService.findByPraticienId(praticienId);
        return ResponseEntity.ok(resultats);
    }

    // Résultats d'un membre (son historique santé)
    @GetMapping("/membre/{membreId}")
    public ResponseEntity<List<Resultat>> getByMembre(@PathVariable Long membreId) {
        List<Resultat> resultats = resultatService.findByMembreId(membreId);
        return ResponseEntity.ok(resultats);
    }

    // Saisie d'un nouveau résultat (par le praticien)
    @PostMapping
    public ResponseEntity<Resultat> createResultat(@Valid @RequestBody Resultat resultat) {
        Resultat created = resultatService.createResultat(resultat);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    // Mise à jour (correction d'une valeur)
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateResultat(@PathVariable Long id, @Valid @RequestBody Resultat resultat) {
        resultat.setId(id);
        Resultat updated = resultatService.updateResultat(resultat);
        return ResponseEntity.ok(updated);
    }

    // Suppression "rare, mais possible"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        resultatService.deleteResultat(id);
        return ResponseEntity.noContent().build();
    }
}