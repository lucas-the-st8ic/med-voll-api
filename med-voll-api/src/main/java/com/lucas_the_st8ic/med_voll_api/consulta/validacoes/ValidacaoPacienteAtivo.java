package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteAtivo {
    public class validacaoPacienteAtivo implements ValidacaoAgendamentoConsulta {

        private PacienteRepository pacienteRepository;

        public void validar (DadosAgendamentoConsulta dados) {
            if (dados.idPaciente() == null) {
                return;
            }

            boolean pacienteEstaAtivo = (boolean) pacienteRepository.findStatusById(dados.idPaciente());

            if (!pacienteEstaAtivo)
                throw new ValidacaoException("Médico não disponível para consultas");

        }
    }
}
