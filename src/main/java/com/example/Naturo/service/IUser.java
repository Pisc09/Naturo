package com.example.Naturo.service;

import com.example.Naturo.request.UserRequest;
import com.example.Naturo.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface IUser {

    List<UserResponse> findAll();

    Optional<UserResponse> findById(Long id);

    Optional<UserResponse> findByEmail(String email);

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

    void toggleEnable(Long id);
}