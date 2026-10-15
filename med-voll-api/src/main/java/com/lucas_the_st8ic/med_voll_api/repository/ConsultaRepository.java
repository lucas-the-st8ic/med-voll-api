package com.lucas_the_st8ic.med_voll_api.repository;

import com.lucas_the_st8ic.med_voll_api.consulta.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByIdAndData(Long idMedico, @NotNull @Future LocalDateTime dataConsulta);

    boolean existsByPacienteIdAndDataBewtween(@NotNull Long idPaciente,
                                              LocalDateTime primeiroHorario,
                                              LocalDateTime ultimoHorario);
}
