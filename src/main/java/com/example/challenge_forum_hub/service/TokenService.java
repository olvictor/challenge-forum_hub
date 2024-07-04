package com.example.challenge_forum_hub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.challenge_forum_hub.domain.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(Usuario usuario){
        try{
            Algorithm algoritimo =  Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("forum_hub")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);

            return token;
        }catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao gerar o token",exception);
        }
    }


    public String validarToken(String token){
        try{
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            return JWT.require(algoritimo)
                    .withIssuer("forum_hub")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw  new RuntimeException("Token inválido ou expirado", exception);
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
