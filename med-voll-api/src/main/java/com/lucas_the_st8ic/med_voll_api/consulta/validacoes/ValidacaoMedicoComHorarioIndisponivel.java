package com.lucas_the_st8ic.med_voll_api.consulta.validacoes;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
import com.lucas_the_st8ic.med_voll_api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoComHorarioIndisponivel implements ValidacaoAgendamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        boolean medicoComHorarioIndisponivel = consultaRepository
                .existsByIdAndData(dados.idMedico(), dados.data());

        if(medicoComHorarioIndisponivel){
            throw new ValidacaoException("Data e horário indisponíveis.");
        }
    }

}
