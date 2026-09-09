package com.lucas_the_st8ic.med_voll_api.medico;

import com.lucas_the_st8ic.med_voll_api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,

        String nome,
        String telefone,
        DadosEndereco endereco) {
}
