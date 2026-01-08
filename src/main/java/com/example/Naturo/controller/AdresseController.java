package com.example.Naturo.controller;

import com.example.Naturo.request.AdresseRequest;
import com.example.Naturo.response.AdresseResponse;
import com.example.Naturo.service.IAdresse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/adresses")
@RequiredArgsConstructor
public class AdresseController {

    private final IAdresse adresseService;

    @GetMapping
    public ResponseEntity<List<AdresseResponse>> getAllAdresses() {
        return ResponseEntity.ok(adresseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseResponse> getAdresseById(@PathVariable Long id) {
        return ResponseEntity.ok(adresseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdresseResponse> createAdresse(@Valid @RequestBody AdresseRequest request) {
        AdresseResponse created = adresseService.createAdresse(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdresseResponse> updateAdresse(@PathVariable Long id, @Valid @RequestBody AdresseRequest request) {
        return ResponseEntity.ok(adresseService.updateAdresse(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        adresseService.deleteAdresse(id);
        return ResponseEntity.noContent().build();
    }
}