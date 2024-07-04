package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.domain.Topico.TopicoRequestDTO;
import com.example.challenge_forum_hub.repository.TopicoRepository;
import com.example.challenge_forum_hub.repository.UsuarioRepository;
import com.example.challenge_forum_hub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    TopicoService topicoService;

    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping
    @Transactional


    public ResponseEntity cadastrar(@RequestBody @Valid TopicoRequestDTO dados){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuarioLogado = usuarioRepository.findByEmail((String) authentication.getPrincipal());

        var topico = topicoService.criar(dados, usuarioLogado.getId());
        topicoRepository.save(topico);
        return ResponseEntity.ok().body(topico);
    }
}
