package com.lucas_the_st8ic.med_voll_api.repository;

import com.lucas_the_st8ic.med_voll_api.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
