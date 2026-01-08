package com.example.Naturo.mapper;

import com.example.Naturo.entity.User;
import com.example.Naturo.request.UserRequest;
import com.example.Naturo.response.UserResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", ignore = true) // géré manuellement (hash)
    @Mapping(target = "enable", constant = "true")
    @Mapping(target = "roles", ignore = true) // géré manuellement
    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(UserRequest request, @MappingTarget User user);
}