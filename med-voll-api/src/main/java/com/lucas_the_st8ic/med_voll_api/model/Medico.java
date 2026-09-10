package com.lucas_the_st8ic.med_voll_api.model;


import com.lucas_the_st8ic.med_voll_api.medico.DadosAtualizacaoMedico;
import com.lucas_the_st8ic.med_voll_api.medico.DadosCadastroMedico;
import com.lucas_the_st8ic.med_voll_api.medico.Especialidade;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    @JdbcTypeCode(SqlTypes.TINYINT)
    private boolean status;

    public Medico(DadosCadastroMedico dados) {
        this.status = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void update(@Valid DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.telefone() != null) this.telefone = dados.telefone();
        if (dados.endereco() != null) this. endereco.update(dados.endereco());
    }

    public void disable(){
        this.status = false;
    }
}
