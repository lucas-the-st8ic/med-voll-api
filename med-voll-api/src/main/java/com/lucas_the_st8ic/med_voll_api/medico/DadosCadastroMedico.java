package com.lucas_the_st8ic.med_voll_api.medico;

import com.lucas_the_st8ic.med_voll_api.endereco.DadosEndereco;
import lombok.ToString;

public record DadosCadastroMedico(String nome,
                                  String email,
                                  String crm,
                                  Especialidade especialidade,
                                  DadosEndereco endereco) {


    @Override
    public String toString() {
        return "DadosCadastroMedico{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade=" + especialidade +
                ", endereco=" + endereco +
                '}';
    }
}
