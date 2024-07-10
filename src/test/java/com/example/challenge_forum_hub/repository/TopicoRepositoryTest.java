package com.example.challenge_forum_hub.repository;


import com.example.challenge_forum_hub.domain.Topico.Topico;
import com.example.challenge_forum_hub.domain.Topico.TopicoRequestDTO;
import com.example.challenge_forum_hub.domain.Topico.TopicoResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando não tiver nenhum topico com titulo e mensagem iguais .")
    void findByTituloAndMensagemCenario1() {

        var topico = topicoRepository.findByTituloAndMensagem("testando","testando mensagem");

        assertThat(topico).isNull();
    }
    @Test
    @DisplayName("Deveria devolver o topico cadastrado .")
    void findByTituloAndMensagemCenario2() {
        var topico = cadastrarTopico();
        var topicoComTituloEmensagemRepetida = topicoRepository.findByTituloAndMensagem("titulo do  topico","mensagem do topico");

        TopicoRequestDTO topicoPersistido = new TopicoRequestDTO(
                topicoComTituloEmensagemRepetida.getTitulo(),
                topicoComTituloEmensagemRepetida.getMensagem(),
                topicoComTituloEmensagemRepetida.getCurso_id()
        );


        TopicoRequestDTO topicoCadastrado = new TopicoRequestDTO(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getCurso_id()
        );

        assertThat(topicoPersistido).isEqualTo(topicoCadastrado);
    }


    private Topico cadastrarTopico (){
        LocalDateTime momentoAtual = LocalDateTime.now();


        Topico topico = em.persist(new Topico("titulo do  topico", "mensagem do topico", 1L,momentoAtual));

      return topico;
    }
}