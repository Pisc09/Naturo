package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Abonnement;
import com.example.Naturo.entity.User;
import com.example.Naturo.entity.enums.TypeAbonnement;
import com.example.Naturo.mapper.AbonnementMapper;
import com.example.Naturo.repository.AbonnementRepository;
import com.example.Naturo.repository.UserRepository;
import com.example.Naturo.request.AbonnementRequest;
import com.example.Naturo.response.AbonnementResponse;
import com.example.Naturo.service.IAbonnement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbonnementService implements IAbonnement {

    private final AbonnementRepository abonnementRepository;
    private final UserRepository userRepository;
    private final AbonnementMapper mapper;

    @Override
    public List<AbonnementResponse> findAll() {
        return mapper.toResponseList(abonnementRepository.findAll());
    }

    @Override
    public List<AbonnementResponse> findByUserId(Long userId) {
        return mapper.toResponseList(
                abonnementRepository.findAll()
                        .stream()
                        .filter(ab -> ab
                                .getUser() != null && ab
                                .getUser().getId()
                                .equals(userId))
                        .toList()
        );
    }

    @Override
    @Transactional
    public AbonnementResponse createAbonnement(AbonnementRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        Abonnement abonnement = mapper.toEntity(request);
        abonnement.setUser(user);

        Abonnement saved = abonnementRepository.save(abonnement);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AbonnementResponse updateAbonnement(Long id, AbonnementRequest request) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Abonnement non trouvé"));

        mapper.updateEntityFromRequest(request, abonnement);

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
            abonnement.setUser(user);
        }

        Abonnement updated = abonnementRepository.save(abonnement);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void desactiverAbonnement(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Abonnement non trouvé"));
        abonnement.setActif(false);
        abonnementRepository.save(abonnement);
    }

    @Override
    public boolean hasAbonnementActif(Long userId, TypeAbonnement typeRequis) {
        return abonnementRepository.findAll().stream()
                .anyMatch(ab -> ab.getUser() != null
                        && ab.getUser().getId().equals(userId)
                        && ab.isActif()
                        && ab.getType().equals(typeRequis)
                        && (ab.getDateFin() == null || ab.getDateFin().isAfter(LocalDate.now())));
    }
}