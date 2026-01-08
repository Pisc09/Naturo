package com.example.Naturo.mapper;

import com.example.Naturo.entity.Abonnement;
import com.example.Naturo.request.AbonnementRequest;
import com.example.Naturo.response.AbonnementResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AbonnementMapper {

    @Mapping(target = "user", ignore = true) // Géré manuellement
    @Mapping(target = "actif", constant = "true")
    @Mapping(target = "dateDebut", source = "dateDebut", defaultExpression = "java(java.time.LocalDate.now())")
    Abonnement toEntity(AbonnementRequest request);

    AbonnementResponse toResponse(Abonnement abonnement);

    List<AbonnementResponse> toResponseList(List<Abonnement> abonnements);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(AbonnementRequest request, @MappingTarget Abonnement abonnement);
}