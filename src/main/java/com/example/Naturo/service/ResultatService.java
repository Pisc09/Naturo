package com.example.Naturo.service;

import com.example.Naturo.entity.Resultat;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.ResultatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResultatService {

    private final ResultatRepository resultatRepository;

    public List<Resultat> findAll() {
        return resultatRepository.findAll();
    }

    // Résultats saisis par un praticien
    public List<Resultat> findByPraticien(User praticien) {
        return resultatRepository.findAll().stream()
                .filter(r -> r.getPraticien().equals(praticien))
                .toList();
    }

    // Résultats concernant un membre
    public List<Resultat> findByMembre(User membre) {
        return resultatRepository.findAll().stream()
                .filter(r -> r.getMembre().equals(membre))
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