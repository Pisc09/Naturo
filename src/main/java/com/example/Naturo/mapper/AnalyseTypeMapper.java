package com.example.Naturo.mapper;

import com.example.Naturo.entity.AnalyseType;
import com.example.Naturo.request.AnalyseTypeRequest;
import com.example.Naturo.response.AnalyseTypeResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalyseTypeMapper {

    AnalyseType toEntity(AnalyseTypeRequest request);

    AnalyseTypeResponse toResponse(AnalyseType analyseType);

    List<AnalyseTypeResponse> toResponseList(List<AnalyseType> analyseTypes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(AnalyseTypeRequest request, @MappingTarget AnalyseType analyseType);
}