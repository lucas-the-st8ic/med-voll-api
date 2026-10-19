package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import com.lucas_the_st8ic.med_voll_api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteSemDuploAgendamento implements ValidacaoAgendamentoConsulta {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        boolean duploAgendamento = consultaRepository
                .existsByPacienteIdAndDataBetween(dados.idPaciente(),
                        primeiroHorario, ultimoHorario);

        if (duploAgendamento) {
            throw new ValidacaoException("Paciente já possui consulta agendada na data.");
        }
    }
}
