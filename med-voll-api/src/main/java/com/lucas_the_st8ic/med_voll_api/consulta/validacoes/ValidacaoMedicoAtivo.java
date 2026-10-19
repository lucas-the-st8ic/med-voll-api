package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoAtivo implements ValidacaoAgendamentoConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        Boolean medicoEstaAtivo = medicoRepository.findStatusById(dados.idMedico());

        System.out.println("Status do Médico: " + medicoEstaAtivo);

        if (!Boolean.TRUE.equals(medicoEstaAtivo)) {
            throw new ValidacaoException("Médico não disponível para consultas");
        }
    }
}
