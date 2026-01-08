package com.example.Naturo.response;

import com.example.Naturo.entity.enums.NiveauAdmin;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminResponse {

    private Long id;

    private String email;

    private String firstname;

    private String lastname;

    private LocalDateTime derniereConnexion;

    private boolean actif;

    private NiveauAdmin niveau;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}