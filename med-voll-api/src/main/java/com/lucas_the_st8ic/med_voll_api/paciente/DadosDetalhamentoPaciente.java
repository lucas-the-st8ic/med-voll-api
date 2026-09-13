package com.lucas_the_st8ic.med_voll_api.paciente;

import com.lucas_the_st8ic.med_voll_api.medico.Especialidade;
import com.lucas_the_st8ic.med_voll_api.model.Endereco;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import com.lucas_the_st8ic.med_voll_api.model.Paciente;

public record DadosDetalhamentoPaciente(Long id,
                                        String nome,
                                        String email,
                                        String cpf,
                                        String telefone,
                                        Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(),
                paciente.getEmail(), paciente.getCpf(),
                paciente.getTelefone(), paciente.getEndereco());
    }
}
