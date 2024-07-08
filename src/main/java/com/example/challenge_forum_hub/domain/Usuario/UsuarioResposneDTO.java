package com.example.challenge_forum_hub.domain.Usuario;

public record UsuarioResposneDTO(
        Long id,
        String nome,
        String email) {

    public UsuarioResposneDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome(), usuario.getEmail());
    }
}
