package com.example.Naturo.controller;

import com.example.Naturo.request.AnalyseTypeRequest;
import com.example.Naturo.response.AnalyseTypeResponse;
import com.example.Naturo.service.IAnalyseType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/analyse-types", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AnalyseTypeController {

    private final IAnalyseType analyseTypeService;

    // Lecture publique (utilisé par les praticiens)
    @GetMapping("/public")
    public ResponseEntity<List<AnalyseTypeResponse>> getAllPublic() {
        return ResponseEntity.ok(analyseTypeService.findAll());
    }

    // Tout (admin)
    @GetMapping
    public ResponseEntity<List<AnalyseTypeResponse>> getAll() {
        return ResponseEntity.ok(analyseTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyseTypeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(analyseTypeService.findById(id));
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<AnalyseTypeResponse> getByNom(@PathVariable String nom) {
        return ResponseEntity.ok(analyseTypeService.findByNom(nom));
    }

    @PostMapping
    public ResponseEntity<AnalyseTypeResponse> create(@Valid @RequestBody AnalyseTypeRequest request) {
        AnalyseTypeResponse created = analyseTypeService.createAnalyseType(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnalyseTypeResponse> update(@PathVariable Long id, @Valid @RequestBody AnalyseTypeRequest request) {
        return ResponseEntity.ok(analyseTypeService.updateAnalyseType(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        analyseTypeService.deleteAnalyseType(id);
        return ResponseEntity.noContent().build();
    }
}