package com.example.Naturo.mapper;

import com.example.Naturo.entity.Resultat;
import com.example.Naturo.request.ResultatRequest;
import com.example.Naturo.response.ResultatResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResultatMapper {

    @Mapping(target = "analyseType", ignore = true)
    @Mapping(target = "membre", ignore = true)
    @Mapping(target = "praticien", ignore = true)
    Resultat toEntity(ResultatRequest request);

    @Mapping(source = "analyseType.id", target = "analyseTypeId")
    @Mapping(source = "analyseType.nom", target = "analyseTypeNom")
    @Mapping(source = "analyseType.unite", target = "unite")
    @Mapping(source = "membre.id", target = "membreId")
    @Mapping(source = "membre.firstname", target = "membreNom")
    @Mapping(source = "praticien.id", target = "praticienId")
    @Mapping(source = "praticien.firstname", target = "praticienNom")
    ResultatResponse toResponse(Resultat resultat);

    List<ResultatResponse> toResponseList(List<Resultat> resultats);

    @Mapping(target = "analyseType", ignore = true)
    @Mapping(target = "membre", ignore = true)
    @Mapping(target = "praticien", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(ResultatRequest request, @MappingTarget Resultat resultat);
}