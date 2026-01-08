package com.example.Naturo.controller;

import com.example.Naturo.request.AbonnementRequest;
import com.example.Naturo.response.AbonnementResponse;
import com.example.Naturo.service.IAbonnement;
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

    private final IAbonnement abonnementService;

    @GetMapping
    public ResponseEntity<List<AbonnementResponse>> getAll() {
        return ResponseEntity.ok(abonnementService.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AbonnementResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(abonnementService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<AbonnementResponse> createAbonnement(@Valid @RequestBody AbonnementRequest request) {
        AbonnementResponse created = abonnementService.createAbonnement(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbonnementResponse> updateAbonnement(@PathVariable Long id, @Valid @RequestBody AbonnementRequest request) {
        AbonnementResponse updated = abonnementService.updateAbonnement(id, request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/desactiver")
    public ResponseEntity<Void> desactiver(@PathVariable Long id) {
        abonnementService.desactiverAbonnement(id);
        return ResponseEntity.noContent().build();
    }
}