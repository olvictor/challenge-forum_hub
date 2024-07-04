package com.example.challenge_forum_hub.domain.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TopicoRequestDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long curso_id
) {
}
