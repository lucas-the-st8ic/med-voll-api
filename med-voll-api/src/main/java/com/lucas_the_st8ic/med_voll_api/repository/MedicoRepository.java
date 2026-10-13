package com.lucas_the_st8ic.med_voll_api.repository;


import com.lucas_the_st8ic.med_voll_api.medico.Especialidade;
import com.lucas_the_st8ic.med_voll_api.model.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    Page<Medico> findAllByStatusTrue(Pageable pageable);


    @Query("""
        SELECT m FROM Medico m
        WHERE m.status = true AND m.especialidade = :especialidade
        AND m.id NOT IN(
        SELECT c.medico.id FROM Consulta c
        WHERE c.data = :data
        )
        ORDER BY RAND()
        LIMIT 1
""")
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}
