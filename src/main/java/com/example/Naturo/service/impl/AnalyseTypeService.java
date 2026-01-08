package com.example.Naturo.service.impl;

import com.example.Naturo.entity.AnalyseType;
import com.example.Naturo.mapper.AnalyseTypeMapper;
import com.example.Naturo.repository.AnalyseTypeRepository;
import com.example.Naturo.request.AnalyseTypeRequest;
import com.example.Naturo.response.AnalyseTypeResponse;
import com.example.Naturo.service.IAnalyseType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalyseTypeService implements IAnalyseType {

    private final AnalyseTypeRepository analyseTypeRepository;
    private final AnalyseTypeMapper mapper;

    @Override
    public List<AnalyseTypeResponse> findAll() {
        return mapper.toResponseList(analyseTypeRepository.findAll());
    }

    @Override
    public AnalyseTypeResponse findById(Long id) {
        AnalyseType analyseType = analyseTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type d'analyse non trouvé avec l'ID : " + id));
        return mapper.toResponse(analyseType);
    }

    @Override
    public AnalyseTypeResponse findByNom(String nom) {
        AnalyseType analyseType = analyseTypeRepository.findByNom(nom)
                .orElseThrow(() -> new IllegalArgumentException("Type d'analyse non trouvé avec le nom : " + nom));
        return mapper.toResponse(analyseType);
    }

    @Override
    @Transactional
    public AnalyseTypeResponse createAnalyseType(AnalyseTypeRequest request) {
        AnalyseType analyseType = mapper.toEntity(request);
        AnalyseType saved = analyseTypeRepository.save(analyseType);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AnalyseTypeResponse updateAnalyseType(Long id, AnalyseTypeRequest request) {
        AnalyseType analyseType = analyseTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type d'analyse non trouvé"));

        mapper.updateEntityFromRequest(request, analyseType);

        AnalyseType updated = analyseTypeRepository.save(analyseType);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteAnalyseType(Long id) {
        if (!analyseTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Type d'analyse non trouvé");
        }
        analyseTypeRepository.deleteById(id);
    }
}