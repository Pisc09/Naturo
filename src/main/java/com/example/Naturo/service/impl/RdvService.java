package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Rdv;
import com.example.Naturo.entity.enums.TypeAbonnement;
import com.example.Naturo.entity.User;
import com.example.Naturo.mapper.RdvMapper;
import com.example.Naturo.repository.RdvRepository;
import com.example.Naturo.repository.UserRepository;
import com.example.Naturo.request.RdvRequest;
import com.example.Naturo.response.RdvResponse;
import com.example.Naturo.service.IRdv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RdvService implements IRdv {

    private final RdvRepository rdvRepository;
    private final UserRepository userRepository;
    private final RdvMapper mapper;
    private final AbonnementService abonnementService; // pour vérifier les abonnements

    @Override
    public List<RdvResponse> findAll() {
        return mapper.toResponseList(rdvRepository.findAll());
    }

    @Override
    public List<RdvResponse> findByPraticienId(Long praticienId) {
        return mapper.toResponseList(
                rdvRepository.findAll().stream()
                        .filter(r -> r.getPraticien() != null && r.getPraticien().getId().equals(praticienId))
                        .toList()
        );
    }

    @Override
    public List<RdvResponse> findByMembreId(Long membreId) {
        return mapper.toResponseList(
                rdvRepository.findAll().stream()
                        .filter(r -> r.getMembre() != null && r.getMembre().getId().equals(membreId))
                        .toList()
        );
    }

    @Override
    @Transactional
    public RdvResponse createRdv(RdvRequest request) {
        User praticien = userRepository.findById(request.getPraticienId())
                .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé"));

        User membre = userRepository.findById(request.getMembreId())
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        // Vérification des abonnements actifs (règle métier)
        boolean praticienOk = abonnementService.hasAbonnementActif(praticien.getId(), TypeAbonnement.PRATICIEN);
        boolean membreOk = abonnementService.hasAbonnementActif(membre.getId(), TypeAbonnement.MEMBRE);

        if (!praticienOk) {
            throw new IllegalStateException("Le praticien doit avoir un abonnement PRATICIEN actif.");
        }
        if (!membreOk) {
            throw new IllegalStateException("Le membre doit avoir un abonnement MEMBRE actif.");
        }

        Rdv rdv = mapper.toEntity(request);
        rdv.setPraticien(praticien);
        rdv.setMembre(membre);

        Rdv saved = rdvRepository.save(rdv);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public RdvResponse updateRdv(Long id, RdvRequest request) {
        Rdv rdv = rdvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rendez-vous non trouvé"));

        mapper.updateEntityFromRequest(request, rdv);

        if (request.getPraticienId() != null) {
            User praticien = userRepository.findById(request.getPraticienId())
                    .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé"));
            rdv.setPraticien(praticien);
        }

        if (request.getMembreId() != null) {
            User membre = userRepository.findById(request.getMembreId())
                    .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));
            rdv.setMembre(membre);
        }

        Rdv updated = rdvRepository.save(rdv);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteRdv(Long id) {
        if (!rdvRepository.existsById(id)) {
            throw new IllegalArgumentException("Rendez-vous non trouvé");
        }
        rdvRepository.deleteById(id);
    }
}