package com.example.challenge_forum_hub.repository;

import com.example.challenge_forum_hub.domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   Usuario findByEmail(String email);
}
