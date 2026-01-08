package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Offre;
import com.example.Naturo.entity.User;
import com.example.Naturo.mapper.OffreMapper;
import com.example.Naturo.repository.OffreRepository;
import com.example.Naturo.repository.UserRepository;
import com.example.Naturo.request.OffreRequest;
import com.example.Naturo.response.OffreResponse;
import com.example.Naturo.service.IOffre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OffreService implements IOffre {

    private final OffreRepository offreRepository;
    private final UserRepository userRepository;
    private final OffreMapper mapper;

    @Override
    public List<OffreResponse> findAll() {
        return mapper.toResponseList(offreRepository.findAll());
    }

    @Override
    public List<OffreResponse> findOffresVisibles() {
        return mapper.toResponseList(
                offreRepository.findAll().stream()
                        .filter(Offre::isVisible)
                        .toList()
        );
    }

    @Override
    public List<OffreResponse> findByPraticienId(Long praticienId) {
        return mapper.toResponseList(
                offreRepository.findAll().stream()
                        .filter(o -> o.getPraticien() != null && o.getPraticien().getId().equals(praticienId))
                        .toList()
        );
    }

    @Override
    @Transactional
    public OffreResponse createOffre(OffreRequest request) {
        User praticien = userRepository.findById(request.getPraticienId())
                .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé"));

        Offre offre = mapper.toEntity(request);
        offre.setPraticien(praticien);

        Offre saved = offreRepository.save(offre);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OffreResponse updateOffre(Long id, OffreRequest request) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre non trouvée"));

        mapper.updateEntityFromRequest(request, offre);

        if (request.getPraticienId() != null) {
            User praticien = userRepository.findById(request.getPraticienId())
                    .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé"));
            offre.setPraticien(praticien);
        }

        if (request.getVisible() != null) {
            offre.setVisible(request.getVisible());
        }

        Offre updated = offreRepository.save(offre);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteOffre(Long id) {
        if (!offreRepository.existsById(id)) {
            throw new IllegalArgumentException("Offre non trouvée");
        }
        offreRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void toggleVisibilite(Long id) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre non trouvée"));
        offre.setVisible(!offre.isVisible());
        offreRepository.save(offre);
    }
}