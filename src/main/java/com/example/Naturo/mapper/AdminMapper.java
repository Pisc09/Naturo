package com.example.Naturo.mapper;

import com.example.Naturo.entity.Admin;
import com.example.Naturo.request.AdminRequest;
import com.example.Naturo.response.AdminResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {

    @Mapping(target = "actif", constant = "true")
    @Mapping(target = "motDePasse", ignore = true) // géré manuellement (hash)
    Admin toEntity(AdminRequest request);

    AdminResponse toResponse(Admin admin);

    List<AdminResponse> toResponseList(List<Admin> admins);

    @Mapping(target = "motDePasse", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "derniereConnexion", ignore = true)
    void updateEntityFromRequest(AdminRequest request, @MappingTarget Admin admin);
}