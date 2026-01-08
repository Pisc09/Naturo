package com.example.Naturo.service;

import com.example.Naturo.request.AdresseRequest;
import com.example.Naturo.response.AdresseResponse;

import java.util.List;

public interface IAdresse {

    List<AdresseResponse> findAll();

    AdresseResponse findById(Long id);

    AdresseResponse createAdresse(AdresseRequest request);

    AdresseResponse updateAdresse(Long id, AdresseRequest request);

    void deleteAdresse(Long id);
}