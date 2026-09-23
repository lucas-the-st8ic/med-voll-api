package com.lucas_the_st8ic.med_voll_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.lucas_the_st8ic.med_voll_api.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String generateToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256("EEJFHBFERKJBEFEJJJBEERREFFBRHJBFFKEJRHFEEURBB@*");
            return JWT.create()
                    .withIssuer("Med-Voll-API")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dateExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao generar JWT", exception);
        }
    }

    private Instant dateExpires() {
        return LocalDateTime.now().plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
