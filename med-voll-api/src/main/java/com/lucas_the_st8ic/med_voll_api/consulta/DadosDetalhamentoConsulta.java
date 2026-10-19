package com.lucas_the_st8ic.med_voll_api.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id,
                                        Long idMedico,
                                        Long idPaciente,
                                        LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),
                consulta.getPaciente().getId(), consulta.getData());
    }
}
