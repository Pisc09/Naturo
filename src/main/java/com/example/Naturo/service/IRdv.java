package com.example.Naturo.service;

import com.example.Naturo.request.RdvRequest;
import com.example.Naturo.response.RdvResponse;

import java.util.List;

public interface IRdv {

    List<RdvResponse> findAll();

    List<RdvResponse> findByPraticienId(Long praticienId);

    List<RdvResponse> findByMembreId(Long membreId);

    RdvResponse createRdv(RdvRequest request);

    RdvResponse updateRdv(Long id, RdvRequest request);

    void deleteRdv(Long id);
}