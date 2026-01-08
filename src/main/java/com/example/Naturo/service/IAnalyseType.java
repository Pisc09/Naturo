package com.example.Naturo.service;

import com.example.Naturo.request.AnalyseTypeRequest;
import com.example.Naturo.response.AnalyseTypeResponse;

import java.util.List;

public interface IAnalyseType {

    List<AnalyseTypeResponse> findAll();

    AnalyseTypeResponse findById(Long id);

    AnalyseTypeResponse findByNom(String nom);

    AnalyseTypeResponse createAnalyseType(AnalyseTypeRequest request);

    AnalyseTypeResponse updateAnalyseType(Long id, AnalyseTypeRequest request);

    void deleteAnalyseType(Long id);
}