package com.lucas_the_st8ic.med_voll_api.repository;

import com.lucas_the_st8ic.med_voll_api.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    UserDetails findByLogin(String login);
}
