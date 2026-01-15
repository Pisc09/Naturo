package com.example.Naturo.service.impl;

import com.example.Naturo.entity.Admin;
import com.example.Naturo.mapper.AdminMapper;
import com.example.Naturo.repository.AdminRepository;
import com.example.Naturo.request.AdminRequest;
import com.example.Naturo.response.AdminResponse;
import com.example.Naturo.service.IAdmin;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdmin, UserDetailsService, UserDetailsPasswordService {

    private final AdminRepository adminRepository;
    private final AdminMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AdminResponse> findAll() {
        return mapper.toResponseList(adminRepository.findAll());
    }

    @Override
    public AdminResponse findById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin non trouvé avec l'ID : " + id));
        return mapper.toResponse(admin);
    }

    @Override
    public AdminResponse findByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Admin non trouvé avec l'email : " + email));
        return mapper.toResponse(admin);
    }

    @Override
    public AdminResponse createAdmin(AdminRequest request) {
        Admin admin = mapper.toEntity(request);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        Admin saved = adminRepository.save(admin);
        return mapper.toResponse(saved);
    }

    @Override
    public AdminResponse updateAdmin(Long id, AdminRequest request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin non trouvé"));

        mapper.updateEntityFromRequest(request, admin);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Admin updated = adminRepository.save(admin);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new IllegalArgumentException("Admin non trouvé");
        }
        adminRepository.deleteById(id);
    }

    @Override
    public void desactiverAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin non trouvé"));
        admin.setActif(false);
        adminRepository.save(admin);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, @Nullable String newPassword) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Admin admin = adminRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin non trouvé avec l'email : " + username));
            return admin;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Admin non trouvé avec l'email : " + username);
        }
    }
}