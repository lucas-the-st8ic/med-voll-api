package com.lucas_the_st8ic.med_voll_api.controller;

import com.lucas_the_st8ic.med_voll_api.consulta.DadosAgendamentoConsulta;
import com.lucas_the_st8ic.med_voll_api.endereco.DadosEndereco;
import com.lucas_the_st8ic.med_voll_api.medico.DadosCadastroMedico;
import com.lucas_the_st8ic.med_voll_api.medico.DadosDetalhamentoMedico;
import com.lucas_the_st8ic.med_voll_api.medico.Especialidade;
import com.lucas_the_st8ic.med_voll_api.model.Endereco;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import com.lucas_the_st8ic.med_voll_api.repository.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private MedicoRepository medicoRepository;

    @Autowired
    private JacksonTester<DadosCadastroMedico> JtDadosCadastroMedico;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> JtDadosDetalhamentoMedico;

    @Test
    @DisplayName("Return 400 when invalid data")
    @WithMockUser
    void register_Cenario1() throws Exception {

        var response = mvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Return 201 when valid data")
    @WithMockUser
    void register_Cenario2() throws Exception {

        var dadosCadastro = new DadosCadastroMedico(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                dadosEndereco());

        when(medicoRepository.save(any()))
                .thenReturn(new Medico(dadosCadastro));

        var response = mvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JtDadosCadastroMedico.write(dadosCadastro)
                                .getJson())
                ).andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoMedico(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.crm(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new Endereco(dadosCadastro.endereco())
        );

        var expectedJson = JtDadosDetalhamentoMedico
                .write(dadosDetalhamento).getJson();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.CREATED.value());

        assertThat(response.getContentAsString())
                .isEqualTo(expectedJson);
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