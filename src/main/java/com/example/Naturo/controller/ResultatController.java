package com.example.Naturo.controller;

import com.example.Naturo.request.ResultatRequest;
import com.example.Naturo.response.ResultatResponse;
import com.example.Naturo.service.IResultat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/resultats", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ResultatController {

    private final IResultat resultatService;

    @GetMapping
    public ResponseEntity<List<ResultatResponse>> getAllResultats() {
        return ResponseEntity.ok(resultatService.findAll());
    }

    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<ResultatResponse>> getByPraticien(@PathVariable Long praticienId) {
        return ResponseEntity.ok(resultatService.findByPraticienId(praticienId));
    }

    @GetMapping("/membre/{membreId}")
    public ResponseEntity<List<ResultatResponse>> getByMembre(@PathVariable Long membreId) {
        return ResponseEntity.ok(resultatService.findByMembreId(membreId));
    }

    @PostMapping
    public ResponseEntity<ResultatResponse> createResultat(@Valid @RequestBody ResultatRequest request) {
        ResultatResponse created = resultatService.createResultat(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultatResponse> updateResultat(@PathVariable Long id, @Valid @RequestBody ResultatRequest request) {
        return ResponseEntity.ok(resultatService.updateResultat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        resultatService.deleteResultat(id);
        return ResponseEntity.noContent().build();
    }
}