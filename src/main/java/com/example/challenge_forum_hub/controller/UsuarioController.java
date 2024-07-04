package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.domain.Usuario.Usuario;
import com.example.challenge_forum_hub.domain.Usuario.UsuarioLoginDTO;
import com.example.challenge_forum_hub.domain.Usuario.UsuarioRequestDTO;
import com.example.challenge_forum_hub.domain.Usuario.UsuarioTOKENDTO;
import com.example.challenge_forum_hub.repository.UsuarioRepository;
import com.example.challenge_forum_hub.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioRequestDTO usuario){
        if (this.usuarioRepository.findByEmail(usuario.email()) != null ){
            return ResponseEntity.badRequest().build();
        }

       var usuarioRegistrado = usuarioRepository.save(new Usuario(usuario));
       return ResponseEntity.ok().body(usuarioRegistrado);
    }


    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody @Valid UsuarioLoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(),data.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok().body(new UsuarioTOKENDTO(token));

    }
}
