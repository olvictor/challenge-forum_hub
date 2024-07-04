package com.example.challenge_forum_hub.service;

import com.example.challenge_forum_hub.domain.Topico.Topico;
import com.example.challenge_forum_hub.domain.Topico.TopicoRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TopicoService {
    public Topico criar(TopicoRequestDTO dados, Long idUsuario){
        LocalDateTime momentoAtual = LocalDateTime.now();
        var topico = new Topico(dados, idUsuario, momentoAtual);

        return topico;
    }
}
