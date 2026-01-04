package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Offre;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.OffreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffreService {

    private final OffreRepository offreRepository;

    public List<Offre> findAll() {
        return offreRepository.findAll();
    }

    public List<Offre> findOffresVisibles() {
        return offreRepository.findAll().stream()
                .filter(Offre::isVisible)
                .toList();
    }

    public List<Offre> findByPraticien(User praticien) {
        return offreRepository.findAll().stream()
                .filter(o -> o.getPraticien() != null && o.getPraticien().equals(praticien))
                .toList();
    }

    public Offre createOffre(Offre offre) {
        offre.setVisible(true);
        return offreRepository.save(offre);
    }

    public Offre updateOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }

    public void toggleVisibilite(Long id) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre non trouvée avec l'ID : " + id));
        offre.setVisible(!offre.isVisible());
        offreRepository.save(offre);
    }
}