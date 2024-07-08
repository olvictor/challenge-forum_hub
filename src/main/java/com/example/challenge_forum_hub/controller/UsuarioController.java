package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.domain.Usuario.*;
import com.example.challenge_forum_hub.repository.UsuarioRepository;
import com.example.challenge_forum_hub.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioRequestDTO usuario, UriComponentsBuilder uriBuilder){
        if (this.usuarioRepository.findByEmail(usuario.email()) != null ){
            return ResponseEntity.badRequest().build();
        }
        var usuarioRegistrado = new Usuario(usuario);
        usuarioRepository.save(usuarioRegistrado);

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioRegistrado.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioResposneDTO(usuarioRegistrado));
    }


    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody @Valid UsuarioLoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(),data.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok().body(new UsuarioTOKENDTO(token));

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarUsuario(@PathVariable Long id){

        var usuario = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new UsuarioResposneDTO(usuario));

    }
}
