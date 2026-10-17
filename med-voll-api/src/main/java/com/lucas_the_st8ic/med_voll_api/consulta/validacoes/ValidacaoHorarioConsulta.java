package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacaoHorarioConsulta implements ValidacaoAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var now = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(now, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Não é possível agendar uma consulta 30 minutos antes do horário.");
        }
    }
}
