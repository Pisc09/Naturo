package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Adresse;
import com.example.Naturo.mapper.AdresseMapper;
import com.example.Naturo.repository.AdresseRepository;
import com.example.Naturo.request.AdresseRequest;
import com.example.Naturo.response.AdresseResponse;
import com.example.Naturo.service.IAdresse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdresseService implements IAdresse {

    private final AdresseRepository adresseRepository;
    private final AdresseMapper mapper;

    @Override
    public List<AdresseResponse> findAll() {
        return mapper.toResponseList(adresseRepository.findAll());
    }

    @Override
    public AdresseResponse findById(Long id) {
        Adresse adresse = adresseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adresse non trouvée avec l'ID : " + id));
        return mapper.toResponse(adresse);
    }

    @Override
    @Transactional
    public AdresseResponse createAdresse(AdresseRequest request) {
        Adresse adresse = mapper.toEntity(request);
        Adresse saved = adresseRepository.save(adresse);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AdresseResponse updateAdresse(Long id, AdresseRequest request) {
        Adresse adresse = adresseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adresse non trouvée"));

        mapper.updateEntityFromRequest(request, adresse);

        Adresse updated = adresseRepository.save(adresse);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteAdresse(Long id) {
        if (!adresseRepository.existsById(id)) {
            throw new IllegalArgumentException("Adresse non trouvée");
        }
        adresseRepository.deleteById(id);
    }
}