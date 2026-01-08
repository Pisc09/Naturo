package com.example.Naturo.service;

import com.example.Naturo.request.OffreRequest;
import com.example.Naturo.response.OffreResponse;

import java.util.List;

public interface IOffre {

    List<OffreResponse> findAll();

    List<OffreResponse> findOffresVisibles();

    List<OffreResponse> findByPraticienId(Long praticienId);

    OffreResponse createOffre(OffreRequest request);

    OffreResponse updateOffre(Long id, OffreRequest request);

    void deleteOffre(Long id);

    void toggleVisibilite(Long id);
}