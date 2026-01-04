package com.example.Naturo.mapper;

import com.example.Naturo.entity.Abonnement;
import com.example.Naturo.request.AbonnementRequest;
import com.example.Naturo.response.AbonnementResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbonnementMapper {

    Abonnement toEntity(AbonnementRequest request);

    AbonnementResponse toResponse(Abonnement abonnement);
}
