package com.example.Naturo.controller;

import com.example.Naturo.request.OffreRequest;
import com.example.Naturo.response.OffreResponse;
import com.example.Naturo.service.IOffre;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/offres", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OffreController {

    private final IOffre offreService;

    // Catalogue public (membres)
    @GetMapping("/public")
    public ResponseEntity<List<OffreResponse>> getOffresPubliques() {
        return ResponseEntity.ok(offreService.findOffresVisibles());
    }

    // Offres d'un praticien (dashboard praticien)
    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<OffreResponse>> getOffresByPraticien(@PathVariable Long praticienId) {
        return ResponseEntity.ok(offreService.findByPraticienId(praticienId));
    }

    // Toutes les offres (admin)
    @GetMapping
    public ResponseEntity<List<OffreResponse>> getAllOffres() {
        return ResponseEntity.ok(offreService.findAll());
    }

    @PostMapping
    public ResponseEntity<OffreResponse> createOffre(@Valid @RequestBody OffreRequest request) {
        OffreResponse created = offreService.createOffre(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OffreResponse> updateOffre(@PathVariable Long id, @Valid @RequestBody OffreRequest request) {
        return ResponseEntity.ok(offreService.updateOffre(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        offreService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/visibilite")
    public ResponseEntity<Void> toggleVisibilite(@PathVariable Long id) {
        offreService.toggleVisibilite(id);
        return ResponseEntity.noContent().build();
    }
}