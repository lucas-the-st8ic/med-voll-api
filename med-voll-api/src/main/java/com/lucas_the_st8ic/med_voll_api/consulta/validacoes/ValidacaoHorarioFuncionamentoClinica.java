package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;


@Component
public class ValidacaoHorarioFuncionamentoClinica implements ValidacaoAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) throws ValidacaoException {
        var dataConsulta = dados.data();

        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        boolean horarioInvalidoParaConsulta = dataConsulta.getHour() < 7 ||
                dataConsulta.getHour() > 18;

        if(domingo || horarioInvalidoParaConsulta){
            throw new ValidacaoException("Consulta fora do horário de atendimento.");
        }
    }
}
