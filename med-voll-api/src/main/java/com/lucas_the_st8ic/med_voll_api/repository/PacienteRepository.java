package com.lucas_the_st8ic.med_voll_api.repository;

import com.lucas_the_st8ic.med_voll_api.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.http.HttpHeaders;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByStatusTrue(Pageable pageable);
}
