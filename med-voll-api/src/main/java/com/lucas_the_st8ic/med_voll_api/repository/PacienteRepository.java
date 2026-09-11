package com.lucas_the_st8ic.med_voll_api.repository;

import com.lucas_the_st8ic.med_voll_api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
