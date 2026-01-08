package com.example.Naturo.service;

import com.example.Naturo.request.ResultatRequest;
import com.example.Naturo.response.ResultatResponse;

import java.util.List;

public interface IResultat {

    List<ResultatResponse> findAll();

    List<ResultatResponse> findByPraticienId(Long praticienId);

    List<ResultatResponse> findByMembreId(Long membreId);

    ResultatResponse createResultat(ResultatRequest request);

    ResultatResponse updateResultat(Long id, ResultatRequest request);

    void deleteResultat(Long id);
}