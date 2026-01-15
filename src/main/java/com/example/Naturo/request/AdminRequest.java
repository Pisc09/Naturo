package com.example.Naturo.request;

import com.example.Naturo.entity.enums.NiveauAdmin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    private String username;

    private String firstname;

    private String lastname;

    @NotNull(message = "Le niveau d'administration est obligatoire")
    private NiveauAdmin niveau;
}