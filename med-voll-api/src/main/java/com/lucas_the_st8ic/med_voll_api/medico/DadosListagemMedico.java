package com.lucas_the_st8ic.med_voll_api.medico;

import com.lucas_the_st8ic.med_voll_api.model.Medico;

public record DadosListagemMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {


    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(),
                medico.getEmail(),
                medico.getCrm(), medico.getEspecialidade());
    }
}
