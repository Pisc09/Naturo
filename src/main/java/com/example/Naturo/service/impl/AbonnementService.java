package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Abonnement;
import com.example.Naturo.entity.enums.TypeAbonnement;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.AbonnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbonnementService {

    private final AbonnementRepository abonnementRepository;

    public List<Abonnement> findAll() {
        return abonnementRepository.findAll();
    }

    public List<Abonnement> findByUser(User user) {
        return abonnementRepository.findAll().stream()
                .filter(ab -> ab.getUser().equals(user))
                .toList();
    }

    // Nouvelle méthode pour le contrôleur
    public List<Abonnement> findByUserId(Long userId) {
        return abonnementRepository.findAll().stream()
                .filter(ab -> ab.getUser() != null && ab.getUser().getId().equals(userId))
                .toList();
    }

    public Abonnement createAbonnement(Abonnement abonnement) {
        abonnement.setDateDebut(LocalDate.now());
        abonnement.setActif(true);
        return abonnementRepository.save(abonnement);
    }

    public Abonnement updateAbonnement(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    public void desactiverAbonnement(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Abonnement non trouvé"));
        abonnement.setActif(false);
        abonnementRepository.save(abonnement);
    }

    public boolean hasAbonnementActif(User user, TypeAbonnement typeRequis) {
        return user.getAbonnements().stream()
                .anyMatch(ab -> ab.isActif()
                        && ab.getType().equals(typeRequis)
                        && (ab.getDateFin() == null || ab.getDateFin().isAfter(LocalDate.now())));
    }
}