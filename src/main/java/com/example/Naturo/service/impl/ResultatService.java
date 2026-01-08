package com.example.Naturo.service.impl;

import com.example.Naturo.entity.AnalyseType;
import com.example.Naturo.entity.Resultat;
import com.example.Naturo.entity.User;
import com.example.Naturo.mapper.ResultatMapper;
import com.example.Naturo.repository.AnalyseTypeRepository;
import com.example.Naturo.repository.ResultatRepository;
import com.example.Naturo.repository.UserRepository;
import com.example.Naturo.request.ResultatRequest;
import com.example.Naturo.response.ResultatResponse;
import com.example.Naturo.service.IResultat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResultatService implements IResultat {

    private final ResultatRepository resultatRepository;
    private final UserRepository userRepository;
    private final AnalyseTypeRepository analyseTypeRepository;
    private final ResultatMapper mapper;

    @Override
    public List<ResultatResponse> findAll() {
        return mapper.toResponseList(resultatRepository.findAll());
    }

    @Override
    public List<ResultatResponse> findByPraticienId(Long praticienId) {
        return mapper.toResponseList(
                resultatRepository.findAll().stream()
                        .filter(r -> r.getPraticien() != null && r.getPraticien().getId().equals(praticienId))
                        .toList()
        );
    }

    @Override
    public List<ResultatResponse> findByMembreId(Long membreId) {
        return mapper.toResponseList(
                resultatRepository.findAll().stream()
                        .filter(r -> r.getMembre() != null && r.getMembre().getId().equals(membreId))
                        .toList()
        );
    }

    @Override
    @Transactional
    public ResultatResponse createResultat(ResultatRequest request) {
        User praticien = userRepository.findById(request.getPraticienId())
                .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé"));

        User membre = userRepository.findById(request.getMembreId())
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        AnalyseType analyseType = analyseTypeRepository.findById(request.getAnalyseTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Type d'analyse non trouvé"));

        Resultat resultat = mapper.toEntity(request);
        resultat.setPraticien(praticien);
        resultat.setMembre(membre);
        resultat.setAnalyseType(analyseType);

        Resultat saved = resultatRepository.save(resultat);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ResultatResponse updateResultat(Long id, ResultatRequest request) {
        Resultat resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Résultat non trouvé"));

        mapper.updateEntityFromRequest(request, resultat);

        if (request.getPraticienId() != null) {
            User praticien = userRepository.findById(request.getPraticienId())
                    .orElseThrow(() -> new IllegalArgumentException("Praticien non trouvé"));
            resultat.setPraticien(praticien);
        }

        if (request.getMembreId() != null) {
            User membre = userRepository.findById(request.getMembreId())
                    .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));
            resultat.setMembre(membre);
        }

        if (request.getAnalyseTypeId() != null) {
            AnalyseType analyseType = analyseTypeRepository.findById(request.getAnalyseTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Type d'analyse non trouvé"));
            resultat.setAnalyseType(analyseType);
        }

        Resultat updated = resultatRepository.save(resultat);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteResultat(Long id) {
        if (!resultatRepository.existsById(id)) {
            throw new IllegalArgumentException("Résultat non trouvé");
        }
        resultatRepository.deleteById(id);
    }
}