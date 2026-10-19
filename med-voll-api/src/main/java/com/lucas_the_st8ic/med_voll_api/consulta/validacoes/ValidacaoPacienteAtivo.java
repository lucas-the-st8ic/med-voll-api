package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteAtivo implements ValidacaoAgendamentoConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {

        if (dados.idPaciente() == null) {
            return;
        }

        Boolean pacienteEstaAtivo =
                pacienteRepository.findStatusById(dados.idPaciente());

        if (!Boolean.TRUE.equals(pacienteEstaAtivo)) {
            throw new ValidacaoException(
                    "Paciente não disponível para consultas"
            );
        }
    }
}
