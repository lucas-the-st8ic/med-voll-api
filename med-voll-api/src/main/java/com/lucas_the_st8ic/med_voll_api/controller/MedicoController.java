package com.lucas_the_st8ic.med_voll_api.controller;


import com.lucas_the_st8ic.med_voll_api.medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class MedicoController {

    @PostMapping("/medicos")
    public void cadastrar (@RequestBody
                           DadosCadastroMedico dados) {
        System.out.println(dados);
    }
}
