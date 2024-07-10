package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.domain.Topico.Topico;
import com.example.challenge_forum_hub.domain.Topico.TopicoRequestDTO;
import com.example.challenge_forum_hub.domain.Topico.TopicoResponseDTO;
import com.example.challenge_forum_hub.repository.TopicoRepository;
import com.example.challenge_forum_hub.repository.UsuarioRepository;
import com.example.challenge_forum_hub.service.TopicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<TopicoRequestDTO> topicoRequestDTOJson;

    @Autowired
    private JacksonTester<TopicoResponseDTO> topicoResponseDTOJson;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockBean
    private TopicoRepository topicoRepository;

    @MockBean
    private TopicoService topicoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deveria devolver o codigo 400 quando informações estão inválidas .")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/topicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deveria devolver a lista de topicos com status 200.")
    @WithMockUser
    void listar_cenario1() throws Exception {
        var response = mvc.perform(get("/topicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(response.getContentAsString()).isNotEmpty();
    }

    @Test
    @DisplayName("Deveria retornar status 403 não autenticado.")
    void listar_cenario2() throws Exception {
        var response = mvc.perform(get("/topicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());

    }


    @Test
    @DisplayName("Deveria retornar status 200 com o conteudo do topico passado no parametro da url.")
    @WithMockUser
    void listar_topico_unico() throws Exception {

        Long topicoId = 1L;
        Topico mockTopico = new Topico();
        mockTopico.setId(topicoId);
        mockTopico.setTitulo("Título de Exemplo");
        mockTopico.setMensagem("Mensagem de Exemplo");

        when(topicoRepository.getReferenceById(topicoId)).thenReturn(mockTopico);

        mvc.perform(get("/topicos/{id}", topicoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Título de Exemplo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Mensagem de Exemplo"));

    }

    @Test
    @DisplayName("Deveria retornar status 200 com o conteudo do topico editado no body da requisicao.")
    @WithMockUser
    void editar_topico() throws Exception {

        Long topicoId = 1L;
        Topico existingTopico = new Topico();
        existingTopico.setId(topicoId);
        existingTopico.setTitulo("Título Antigo");
        existingTopico.setMensagem("Mensagem Antiga");
        existingTopico.setCurso_id(1L);

        Topico updateRequest = new Topico();
        updateRequest.setTitulo("Novo Título");
        updateRequest.setMensagem("Nova Mensagem");
        updateRequest.setCurso_id(1L);

        // Simula o retorno do tópico existente pelo ID
        when(topicoRepository.getReferenceById(topicoId)).thenReturn(existingTopico);

        // Simula a atualização do tópico com os novos dados
        when(topicoRepository.save(ArgumentMatchers.any(Topico.class))).thenReturn(existingTopico);

        mvc.perform(MockMvcRequestBuilders.put("/topicos/{id}", topicoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Novo Título"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Nova Mensagem"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.curso_id").value(1L));

    }


    @Test
    @DisplayName("Deveria retornar status 200 com o conteudo do topico editado no body da requisicao.")
    @WithMockUser
    void deletar_topico() throws Exception {

        Long topicoId = 1L;
        Topico existingTopico = new Topico();
        existingTopico.setId(topicoId);
        existingTopico.setTitulo("Título Antigo");
        existingTopico.setMensagem("Mensagem Antiga");
        existingTopico.setCurso_id(1L);


        // Simula o retorno do tópico existente pelo ID
        when(topicoRepository.getReferenceById(topicoId)).thenReturn(existingTopico);


        mvc.perform(MockMvcRequestBuilders.delete("/topicos/{id}", topicoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}