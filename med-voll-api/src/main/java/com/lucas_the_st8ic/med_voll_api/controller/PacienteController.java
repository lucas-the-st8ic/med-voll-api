package com.lucas_the_st8ic.med_voll_api.controller;

import com.lucas_the_st8ic.med_voll_api.model.Paciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosAtualizacaoPaciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosCadastroPaciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosListagemPaciente;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void register (@RequestBody DadosCadastroPaciente dados){
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listAll(@PageableDefault(size = 5, page = 0, sort = {"nome"})
                                             Pageable pageable) {
        return pacienteRepository.findAllByStatusTrue(pageable)
                .map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void update (@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.update(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete (@PathVariable @Valid Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.disable();
    }

    /*   Exclusão Física - remove do banco de dados
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void delete (@PathVariable @Valid Long id){
        pacienteRepository.deleteById(id);
    }*/
}
