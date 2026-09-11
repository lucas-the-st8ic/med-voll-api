package com.lucas_the_st8ic.med_voll_api.paciente;

import com.lucas_the_st8ic.med_voll_api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,

        String nome,
        String telefone,
        DadosEndereco endereco,
        String email
) { }
