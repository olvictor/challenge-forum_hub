package com.example.challenge_forum_hub.domain.Topico;

import com.example.challenge_forum_hub.domain.Curso.Curso;
import com.example.challenge_forum_hub.domain.Resposta.Resposta;
import com.example.challenge_forum_hub.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long id;
   private String titulo;
   private String mensagem;
   private LocalDateTime dataCriacao;
   private boolean status;

   private Usuario autor;
   private Curso curso;
   private Resposta respostas;
}
