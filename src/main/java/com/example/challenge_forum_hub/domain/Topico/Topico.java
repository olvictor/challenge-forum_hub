package com.example.challenge_forum_hub.domain.Topico;

import com.example.challenge_forum_hub.domain.Resposta.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
   @Column(name = "data_criacao")
   private LocalDateTime dataCriacao;
   private boolean status;

   private Long autor_id;
   private Long curso_id;

   @OneToMany(mappedBy = "topico",fetch = FetchType.EAGER)
   private List<Resposta> respostas;


   public Topico(TopicoRequestDTO dados, Long idUsuario, LocalDateTime momentoAtual) {
      this.autor_id = idUsuario;
      this.titulo = dados.titulo();
      this.mensagem = dados.mensagem();
      this.curso_id = dados.curso_id();
      this.dataCriacao = momentoAtual;
      this.status = true;
   }

   public void editarDados(TopicoRequestDTO dados) {
      if(dados.titulo() != null){
         this.titulo = dados.titulo();
      }
      if(dados.mensagem() != null){
         this.mensagem = dados.mensagem();
      }
      if(dados.curso_id() != null){
         this.curso_id = dados.curso_id();
      }

   }

   public void deletar() {
      this.status = false;
   }
}
