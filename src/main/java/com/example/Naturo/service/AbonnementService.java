package com.example.Naturo.service;

import com.example.Naturo.entity.Abonnement;
import com.example.Naturo.entity.enums.TypeAbonnement;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.AbonnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    // Création d'un nouvel abonnement
    @Transactional
    public Abonnement createAbonnement(Abonnement abonnement) {
        abonnement.setDateDebut(LocalDate.now());
        abonnement.setActif(true);
        return abonnementRepository.save(abonnement);
    }

    // Renouvellement ou mise à jour
    @Transactional
    public Abonnement updateAbonnement(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    // Désactivation (expiration ou annulation)
    @Transactional
    public void desactiverAbonnement(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Abonnement non trouvé"));
        abonnement.setActif(false);
        abonnementRepository.save(abonnement);
    }

    // Vérifie si l'utilisateur a un abonnement actif du type requis
    public boolean hasAbonnementActif(User user, TypeAbonnement typeRequis) {
        return user.getAbonnements().stream()
                .anyMatch(ab -> ab.isActif()
                        && ab.getType().equals(typeRequis)
                        && (ab.getDateFin() == null || ab.getDateFin().isAfter(LocalDate.now())));
    }
}