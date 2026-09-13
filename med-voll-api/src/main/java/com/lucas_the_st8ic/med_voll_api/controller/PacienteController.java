package com.lucas_the_st8ic.med_voll_api.controller;

import com.lucas_the_st8ic.med_voll_api.paciente.DadosCadastroPaciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosDetalhamentoPaciente;
import com.lucas_the_st8ic.med_voll_api.model.Paciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosAtualizacaoPaciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosListagemPaciente;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid
                                   DadosCadastroPaciente dados,
                                   UriComponentsBuilder uriBuilder) {

        var paciente = new Paciente(dados);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}")
                .buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));

    }

    @GetMapping
    public ResponseEntity < Page<DadosListagemPaciente> > listAll(@PageableDefault(size = 5, page = 0, sort = {"nome"})
                                             Pageable pageable) {
        var page = pacienteRepository.findAllByStatusTrue(pageable)
                .map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> listPacienteById(@PathVariable Long id) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException());

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.update(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable @Valid Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.disable();

        return ResponseEntity.noContent().build();
    }

    /*   Exclusão Física - remove do banco de dados
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void delete (@PathVariable @Valid Long id){
        pacienteRepository.deleteById(id);
    }*/
}
