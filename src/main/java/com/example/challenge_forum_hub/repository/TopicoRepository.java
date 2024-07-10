package com.example.challenge_forum_hub.repository;

import com.example.challenge_forum_hub.domain.Topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
   Topico findByTitulo(String titulo);
   Topico findByid(Long id);
   Topico findByTituloAndMensagem(String titulo, String mensagem);
}
