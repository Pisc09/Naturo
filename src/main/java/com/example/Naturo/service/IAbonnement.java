package com.example.Naturo.service;

import com.example.Naturo.request.AbonnementRequest;
import com.example.Naturo.response.AbonnementResponse;

import java.util.List;

public interface IAbonnement {

    List<AbonnementResponse> findAll();

    List<AbonnementResponse> findByUserId(Long userId);

    AbonnementResponse createAbonnement(AbonnementRequest request);

    AbonnementResponse updateAbonnement(Long id, AbonnementRequest request);

    void desactiverAbonnement(Long id);

    boolean hasAbonnementActif(Long userId, com.example.Naturo.entity.enums.TypeAbonnement typeRequis);
}