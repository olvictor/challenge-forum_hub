package com.example.challenge_forum_hub.repository;

import com.example.challenge_forum_hub.domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   UserDetails findByEmail(String email);
}
