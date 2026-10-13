package com.lucas_the_st8ic.med_voll_api.consulta;

import com.lucas_the_st8ic.med_voll_api.infra.exception.ResourceNotFoundException;
import com.lucas_the_st8ic.med_voll_api.repository.ConsultaRepository;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;
import com.lucas_the_st8ic.med_voll_api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ResourceNotFoundException("Id do paciente não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ResourceNotFoundException("Id do medico não existe");
        }

        var medico = medicoRepository.findById(dados.idMedico()).get();
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }
}
