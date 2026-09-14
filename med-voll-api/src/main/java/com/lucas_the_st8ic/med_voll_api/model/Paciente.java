package com.lucas_the_st8ic.med_voll_api.model;


import com.lucas_the_st8ic.med_voll_api.paciente.DadosAtualizacaoPaciente;
import com.lucas_the_st8ic.med_voll_api.paciente.DadosCadastroPaciente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    @JdbcTypeCode(SqlTypes.TINYINT)
    private boolean status;

    public Paciente(DadosCadastroPaciente dados) {
        this.status = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf().replaceAll("\\D", "");
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void update(@Valid DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.telefone() != null) this.telefone = dados.telefone();
        if (dados.cpf() != null) {
            this.cpf = dados.cpf().replaceAll("\\D", "");
        }
        if (dados.endereco() != null) this. endereco.update(dados.endereco());
        if (dados.email()!= null) this.email = dados.email();
    }

    public void disable(){
        this.status = false;
    }
}
