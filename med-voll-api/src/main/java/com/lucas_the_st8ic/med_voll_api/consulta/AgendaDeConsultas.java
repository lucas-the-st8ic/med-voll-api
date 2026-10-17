package com.lucas_the_st8ic.med_voll_api.consulta;

import com.lucas_the_st8ic.med_voll_api.consulta.validacoes.ValidacaoAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ResourceNotFoundException;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import com.lucas_the_st8ic.med_voll_api.repository.ConsultaRepository;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    @Autowired
    private List<ValidacaoAgendamentoConsulta> validacoes;

    public void agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ResourceNotFoundException("Id do paciente não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ResourceNotFoundException("Id do medico não existe");
        }

        validacoes.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ResourceNotFoundException("O campo especialidade é obrigatório");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
