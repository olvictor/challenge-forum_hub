package com.example.challenge_forum_hub.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public  ResponseEntity itemDuplicado(RuntimeException ex){
        return  ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity tratarErro400(MethodArgumentNotValidException ex){

        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }


    private record DadosErroValidacao(String campo,String mensagem){
        public DadosErroValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
