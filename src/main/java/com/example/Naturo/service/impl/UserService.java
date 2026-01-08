package com.example.Naturo.service.impl;

import com.example.Naturo.entity.User;
import com.example.Naturo.mapper.UserMapper;
import com.example.Naturo.repository.UserRepository;
import com.example.Naturo.request.UserRequest;
import com.example.Naturo.response.UserResponse;
import com.example.Naturo.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUser {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return mapper.toResponseList(userRepository.findAll());
    }

    @Override
    public Optional<UserResponse> findById(Long id) {
        return userRepository.findById(id)
                .map(mapper::toResponse);
    }

    @Override
    public Optional<UserResponse> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toResponse);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Rôles par défaut selon le type d'inscription (à adapter selon ta logique)
        user.setRoles(new HashSet<>()); // ex. : ajouter "MEMBRE" ou "PRATICIEN" selon le formulaire

        User saved = userRepository.save(user);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        mapper.updateEntityFromRequest(request, user);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updated = userRepository.save(user);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void toggleEnable(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        user.setEnable(!user.isEnable());
        userRepository.save(user);
    }
}