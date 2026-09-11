package com.lucas_the_st8ic.med_voll_api.controller;

import com.lucas_the_st8ic.med_voll_api.model.Paciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosCadastroPaciente;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
@EnableSpringDataWebSupport(pageSerializationMode =
        EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping("/cadastar")
    public void register (@RequestBody DadosCadastroPaciente dados){
        pacienteRepository.save(new Paciente(dados));
    }
}
