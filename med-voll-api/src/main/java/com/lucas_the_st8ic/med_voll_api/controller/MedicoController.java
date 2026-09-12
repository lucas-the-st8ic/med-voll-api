package com.lucas_the_st8ic.med_voll_api.controller;


import com.lucas_the_st8ic.med_voll_api.medico.DadosAtualizacaoMedico;
import com.lucas_the_st8ic.med_voll_api.medico.DadosCadastroMedico;
import com.lucas_the_st8ic.med_voll_api.medico.DadosDetalhamentoMedico;
import com.lucas_the_st8ic.med_voll_api.medico.DadosListagemMedico;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@EnableSpringDataWebSupport(pageSerializationMode =
        EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid
                                       DadosCadastroMedico dados,
                                   UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}")
                .buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping
    public ResponseEntity< Page<DadosListagemMedico> > listAll(@PageableDefault(size = 5, page = 0, sort = {"nome"})
                                                 Pageable pageable) {

        var page = medicoRepository.findAllByStatusTrue(pageable)
                .map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid DadosAtualizacaoMedico dados) {
       var medico = medicoRepository.getReferenceById(dados.id());
       medico.update(dados);

       return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable @Valid Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.disable();

        return ResponseEntity.noContent().build();
    }

/*   Exclusão Física - remove do banco de dados
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void delete (@PathVariable @Valid Long id){
        medicoRepository.deleteById(id);
    }*/
}
