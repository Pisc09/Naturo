package com.example.Naturo.service;

import com.example.Naturo.request.AdminRequest;
import com.example.Naturo.response.AdminResponse;

import java.util.List;

public interface IAdmin {

    List<AdminResponse> findAll();

    AdminResponse findById(Long id);

    AdminResponse findByEmail(String email);

    AdminResponse createAdmin(AdminRequest request);

    AdminResponse updateAdmin(Long id, AdminRequest request);

    void deleteAdmin(Long id);

    void desactiverAdmin(Long id);
}