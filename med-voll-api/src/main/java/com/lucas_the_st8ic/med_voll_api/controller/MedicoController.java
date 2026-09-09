package com.lucas_the_st8ic.med_voll_api.controller;


import com.lucas_the_st8ic.med_voll_api.medico.DadosCadastroMedico;
import com.lucas_the_st8ic.med_voll_api.medico.DadosListagemMedico;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
@EnableSpringDataWebSupport(pageSerializationMode =
        EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class MedicoController {

    @Autowired
    private  MedicoRepository medicoRepository;

    @PostMapping("/cadastrar")
    @Transactional
    public void cadastrar (@RequestBody
                            @Valid DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listAll(@PageableDefault(size = 2, page = 0, sort = {"nome"})
                                                 Pageable pageable) {
        return medicoRepository.findAll(pageable)
                .map(DadosListagemMedico::new);
    }
}
