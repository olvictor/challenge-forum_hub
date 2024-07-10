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

        var topicoComTituloEMensagemExistente = topicoRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if(topicoComTituloEMensagemExistente != null){
            throw new RuntimeException("Tópico com o mesmo titulo e mensagem já existentes no banco de dados");
        }

        return topico;
    }
}
