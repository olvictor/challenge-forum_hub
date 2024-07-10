package com.example.challenge_forum_hub.controller;
import com.example.challenge_forum_hub.domain.Topico.Topico;
import com.example.challenge_forum_hub.domain.Topico.TopicoRequestDTO;
import com.example.challenge_forum_hub.domain.Topico.TopicoResponseDTO;
import com.example.challenge_forum_hub.repository.TopicoRepository;
import com.example.challenge_forum_hub.repository.UsuarioRepository;
import com.example.challenge_forum_hub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    TopicoService topicoService;

    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid TopicoRequestDTO dados, UriComponentsBuilder uriBuilder){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuarioLogado = usuarioRepository.findByEmail((String) authentication.getPrincipal());

        var topico = topicoService.criar(dados, usuarioLogado.getId());
        topicoRepository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoResponseDTO(topico));
    }


    @GetMapping
    public Page<TopicoResponseDTO> listar(@PageableDefault(size = 10,sort = {"dataCriacao"}) Pageable pageable){
            return topicoRepository.findAll(pageable).map(TopicoResponseDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicoResponseDTO(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoRequestDTO dados){
       var topicoExistente = topicoRepository.getReferenceById(id);
       var topicoComTituloEMensagemExistente = topicoRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());

       if (topicoExistente != null && topicoComTituloEMensagemExistente == null){
           topicoExistente.editarDados(dados);
           topicoRepository.save(topicoExistente);

           return ResponseEntity.ok(new TopicoResponseDTO(topicoExistente));
       }
       throw  new RuntimeException("Tópico com o mesmo titulo e mensagem já existentes no banco de dados");

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.deletar();
        return ResponseEntity.noContent().build();
    }
}
