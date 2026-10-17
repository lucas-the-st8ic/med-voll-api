package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;

public class ValidacaoMedicoAtivo implements ValidacaoAgendamentoConsulta {

    private MedicoRepository medicoRepository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        boolean medicoEstaAtivo = (boolean) medicoRepository.findStatusById(dados.idMedico());

        if (!medicoEstaAtivo)
                    throw new ValidacaoException("Médico não disponível para consultas");

    }
}
