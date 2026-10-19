package com.lucas_the_st8ic.med_voll_api.consulta;

import com.lucas_the_st8ic.med_voll_api.consulta.validacoes.ValidacaoAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.infra.exception.ValidacaoException;
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

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do medico não existe");
        }

        validacoes.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidacaoException("Nenhum médico disponível nessa data.");

        }

        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("O campo especialidade é obrigatório");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
