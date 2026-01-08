package com.example.Naturo.mapper;

import com.example.Naturo.entity.Offre;
import com.example.Naturo.request.OffreRequest;
import com.example.Naturo.response.OffreResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OffreMapper {

    @Mapping(target = "praticien", ignore = true)
    @Mapping(target = "visible", constant = "true")
    Offre toEntity(OffreRequest request);

    OffreResponse toResponse(Offre offre);

    List<OffreResponse> toResponseList(List<Offre> offres);

    @Mapping(target = "praticien", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(OffreRequest request, @MappingTarget Offre offre);
}