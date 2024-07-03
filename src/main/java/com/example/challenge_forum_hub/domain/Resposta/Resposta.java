package com.example.challenge_forum_hub.domain.Resposta;

import com.example.challenge_forum_hub.domain.Topico.Topico;
import com.example.challenge_forum_hub.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @OneToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
