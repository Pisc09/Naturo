package com.example.Naturo.controller;

import com.example.Naturo.entity.Rdv;
import com.example.Naturo.service.impl.RdvService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rdv")
@RequiredArgsConstructor
public class RdvController {

    private final RdvService rdvService;

    @GetMapping
    public ResponseEntity<List<Rdv>> getAllRdv() {
        return ResponseEntity.ok(rdvService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rdv> getRdvById(@PathVariable Long id) {
        // À implémenter avec findById quand tu ajoutes la méthode dans le service
        return ResponseEntity.ok().build(); // placeholder
    }

    @PostMapping
    public ResponseEntity<Rdv> createRdv(@Valid @RequestBody Rdv rdv) {
        Rdv created = rdvService.createRdv(rdv);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRdv(@PathVariable Long id) {
        rdvService.deleteRdv(id);
        return ResponseEntity.noContent().build();
    }
}