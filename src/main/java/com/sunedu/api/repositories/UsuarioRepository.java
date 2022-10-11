package com.sunedu.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunedu.api.model.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, String>{

    
}