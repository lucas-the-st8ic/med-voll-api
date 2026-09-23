package com.lucas_the_st8ic.med_voll_api.controller;


import com.lucas_the_st8ic.med_voll_api.infra.security.TokenService;
import com.lucas_the_st8ic.med_voll_api.usuario.DadosAutenticacao;
import com.lucas_the_st8ic.med_voll_api.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AutenticacaoController {


    private final AuthenticationManager manager;

    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody @Valid
                                DadosAutenticacao dados) {

        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.generateToken((Usuario) authentication.getPrincipal()));
    }
}
