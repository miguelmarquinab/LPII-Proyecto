package com.lp2.sisinventario.sistemainventario.repository;

import com.lp2.sisinventario.sistemainventario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}