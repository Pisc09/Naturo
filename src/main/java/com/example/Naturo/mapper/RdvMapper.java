package com.example.Naturo.mapper;

import com.example.Naturo.entity.Rdv;
import com.example.Naturo.request.RdvRequest;
import com.example.Naturo.response.RdvResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RdvMapper {

    @Mapping(target = "praticien", ignore = true)
    @Mapping(target = "membre", ignore = true)
    Rdv toEntity(RdvRequest request);

    @Mapping(source = "praticien.id", target = "praticienId")
    @Mapping(source = "praticien.firstname", target = "praticienNom") // à adapter selon ton champ nom dans User
    @Mapping(source = "membre.id", target = "membreId")
    @Mapping(source = "membre.firstname", target = "membreNom")
    RdvResponse toResponse(Rdv rdv);

    List<RdvResponse> toResponseList(List<Rdv> rdvs);

    @Mapping(target = "praticien", ignore = true)
    @Mapping(target = "membre", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(RdvRequest request, @MappingTarget Rdv rdv);
}