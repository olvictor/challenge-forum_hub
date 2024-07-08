package com.example.challenge_forum_hub.service;

import com.example.challenge_forum_hub.domain.Topico.Topico;
import com.example.challenge_forum_hub.domain.Topico.TopicoRequestDTO;
import com.example.challenge_forum_hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    public Topico criar(TopicoRequestDTO dados, Long idUsuario){

        LocalDateTime momentoAtual = LocalDateTime.now();
        var topico = new Topico(dados, idUsuario, momentoAtual);

        var topicoComTituloExistente = topicoRepository.findByTitulo(dados.titulo());
        if(topicoComTituloExistente.getMensagem().equals(dados.mensagem())){
            throw new RuntimeException("Tópico já existente.");
        }

        return topico;
    }
}
