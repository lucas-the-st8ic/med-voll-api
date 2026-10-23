package com.lucas_the_st8ic.med_voll_api.repository;

import com.lucas_the_st8ic.med_voll_api.consulta.Consulta;
import com.lucas_the_st8ic.med_voll_api.endereco.DadosEndereco;
import com.lucas_the_st8ic.med_voll_api.medico.DadosCadastroMedico;
import com.lucas_the_st8ic.med_voll_api.medico.Especialidade;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import com.lucas_the_st8ic.med_voll_api.model.Paciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosCadastroPaciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Return null when Doctor is not available on the date.")
    void escolherMedicoAleatorioLivreNaDataCenario1() {

        //given ou arrange
        var specialty = Especialidade.CARDIOLOGIA;
        var mondayAt10 = LocalDateTime.now()
                .with(TemporalAdjusters
                        .next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(10,0);


        var medico = cadastrarMedico("Medico Teste",
                "medico@voll.med", "123456",
                Especialidade.CARDIOLOGIA);

        var paciente = cadastrarPaciente("Paciente teste",
                "paciente@voll.med", "12345678900");

        cadastrarConsulta(medico, paciente, mondayAt10);


        //when ou act
        var availableDoctor = medicoRepository
                .escolherMedicoAleatorioLivreNaData(specialty, mondayAt10);

        //then ou assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Return the doctor when he is available on the date.")
    void escolherMedicoAleatorioLivreNaDataCenario2() {

        //given ou arrange
        var specialty = Especialidade.CARDIOLOGIA;
        var mondayAt10 = LocalDateTime.now()
                .with(TemporalAdjusters
                        .next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(10,0);

        var medico = cadastrarMedico("Medico Teste",
                "medico@voll.med", "123456",
                Especialidade.CARDIOLOGIA);

        //when ou act
        var availableDoctor = medicoRepository
                .escolherMedicoAleatorioLivreNaData(specialty, mondayAt10);

        //then ou assert
        assertThat(availableDoctor).isEqualTo(medico);
    }


    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}