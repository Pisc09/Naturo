package com.example.Naturo.controller;

import com.example.Naturo.request.RdvRequest;
import com.example.Naturo.response.RdvResponse;
import com.example.Naturo.service.IRdv;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rdv", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RdvController {

    private final IRdv rdvService;

    // Tous les RDV (admin)
    @GetMapping
    public ResponseEntity<List<RdvResponse>> getAll() {
        return ResponseEntity.ok(rdvService.findAll());
    }

    // Agenda d'un praticien
    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<RdvResponse>> getByPraticien(@PathVariable Long praticienId) {
        return ResponseEntity.ok(rdvService.findByPraticienId(praticienId));
    }

    // RDV pris par un membre
    @GetMapping("/membre/{membreId}")
    public ResponseEntity<List<RdvResponse>> getByMembre(@PathVariable Long membreId) {
        return ResponseEntity.ok(rdvService.findByMembreId(membreId));
    }

    // Création d'un RDV (par le membre)
    @PostMapping
    public ResponseEntity<RdvResponse> createRdv(@Valid @RequestBody RdvRequest request) {
        RdvResponse created = rdvService.createRdv(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    // Mise à jour (ex. annulation ou report)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RdvResponse> updateRdv(@PathVariable Long id, @Valid @RequestBody RdvRequest request) {
        return ResponseEntity.ok(rdvService.updateRdv(id, request));
    }

    // Suppression (annulation définitive)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRdv(@PathVariable Long id) {
        rdvService.deleteRdv(id);
        return ResponseEntity.noContent().build();
    }
}