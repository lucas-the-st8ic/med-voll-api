package com.lucas_the_st8ic.med_voll_api.repository;


import com.lucas_the_st8ic.med_voll_api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    Page<Medico> findAllByStatusTrue(Pageable pageable);
}
