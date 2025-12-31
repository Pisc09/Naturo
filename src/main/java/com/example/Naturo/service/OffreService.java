package com.example.Naturo.service;

import com.example.Naturo.entity.Offre;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.OffreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OffreService {

    private final OffreRepository offreRepository;

    public List<Offre> findAll() {
        return offreRepository.findAll();
    }

    // Offres visibles pour les membres "catalog public"
    public List<Offre> findOffresVisibles() {
        return offreRepository.findAll().stream()
                .filter(Offre::isVisible)
                .toList();
    }

    // Offres d'un praticien spécifique
    public List<Offre> findByPraticien(User praticien) {
        return offreRepository.findAll().stream()
                .filter(o -> o.getPraticien().equals(praticien))
                .toList();
    }

    @Transactional
    public Offre createOffre(Offre offre) {
        offre.setVisible(true); // par défaut visible
        return offreRepository.save(offre);
    }

    @Transactional
    public Offre updateOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Transactional
    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }

    @Transactional
    public void toggleVisibilite(Long id) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre non trouvée"));
        offre.setVisible(!offre.isVisible());
        offreRepository.save(offre);
    }
}