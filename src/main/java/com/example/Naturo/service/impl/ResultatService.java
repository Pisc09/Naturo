package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Resultat;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.ResultatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultatService {

    private final ResultatRepository resultatRepository;

    public List<Resultat> findAll() {
        return resultatRepository.findAll();
    }

    public List<Resultat> findByPraticien(User praticien) {
        return resultatRepository.findAll().stream()
                .filter(r -> r.getPraticien().equals(praticien))
                .toList();
    }

    public List<Resultat> findByMembre(User membre) {
        return resultatRepository.findAll().stream()
                .filter(r -> r.getMembre().equals(membre))
                .toList();
    }

    // Nouvelles méthodes pour le contrôleur
    public List<Resultat> findByPraticienId(Long praticienId) {
        return resultatRepository.findAll().stream()
                .filter(r -> r.getPraticien() != null && r.getPraticien().getId().equals(praticienId))
                .toList();
    }

    public List<Resultat> findByMembreId(Long membreId) {
        return resultatRepository.findAll().stream()
                .filter(r -> r.getMembre() != null && r.getMembre().getId().equals(membreId))
                .toList();
    }

    @Transactional
    public Resultat createResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    @Transactional
    public Resultat updateResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    @Transactional
    public void deleteResultat(Long id) {
        resultatRepository.deleteById(id);
    }
}