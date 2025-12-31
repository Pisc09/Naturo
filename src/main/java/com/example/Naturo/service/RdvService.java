package com.example.Naturo.service;

import com.example.Naturo.entity.Rdv;
import com.example.Naturo.entity.enums.TypeAbonnement;
import com.example.Naturo.entity.User;
import com.example.Naturo.repository.RdvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RdvService {

    private final RdvRepository rdvRepository;
    private final UserService userService;

    public List<Rdv> findAll() {
        return rdvRepository.findAll();
    }

    public List<Rdv> findByPraticien(User praticien) {
        return rdvRepository.findAll().stream()
                .filter(r -> r.getPraticien().equals(praticien))
                .toList();
    }

    public List<Rdv> findByMembre(User membre) {
        return rdvRepository.findAll().stream()
                .filter(r -> r.getMembre().equals(membre))
                .toList();
    }

    @Transactional
    public Rdv createRdv(Rdv rdv) {
        User praticien = rdv.getPraticien();
        User membre = rdv.getMembre();

        boolean praticienOk = userService.hasAbonnementActif(praticien, TypeAbonnement.PRATICIEN);
        boolean membreOk = userService.hasAbonnementActif(membre, TypeAbonnement.MEMBRE);

        if (!praticienOk) {
            throw new IllegalStateException("Le praticien doit avoir un abonnement PRATICIEN actif.");
        }

        if (!membreOk) {
            throw new IllegalStateException("Le membre doit avoir un abonnement MEMBRE actif.");
        }

        return rdvRepository.save(rdv);
    }

    @Transactional
    public void deleteRdv(Long id) {
        rdvRepository.deleteById(id);
    }
}