package com.example.Naturo.mapper;

import com.example.Naturo.entity.Adresse;
import com.example.Naturo.request.AdresseRequest;
import com.example.Naturo.response.AdresseResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdresseMapper {

    Adresse toEntity(AdresseRequest request);

    AdresseResponse toResponse(Adresse adresse);

    List<AdresseResponse> toResponseList(List<Adresse> adresses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(AdresseRequest request, @MappingTarget Adresse adresse);
}