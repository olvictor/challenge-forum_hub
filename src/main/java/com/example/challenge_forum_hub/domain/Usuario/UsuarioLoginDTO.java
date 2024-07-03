package com.example.challenge_forum_hub.domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @NotBlank
        @Email
        String usuario,
        @NotBlank
        String senha) {
}
