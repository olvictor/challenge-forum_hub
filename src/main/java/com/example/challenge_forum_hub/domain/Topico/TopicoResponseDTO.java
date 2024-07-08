package com.example.challenge_forum_hub.domain.Topico;

import java.time.LocalDateTime;

public record TopicoResponseDTO(
        String titulo,
        String mensagem,
        LocalDateTime data_criacao,
        boolean status,
        Long autor_id,
        Long curso_id
) {
    public TopicoResponseDTO(Topico topico){
        this(topico.getTitulo(),topico.getMensagem(),topico.getDataCriacao(), topico.isStatus(), topico.getAutor_id(), topico.getCurso_id());
    }
}
