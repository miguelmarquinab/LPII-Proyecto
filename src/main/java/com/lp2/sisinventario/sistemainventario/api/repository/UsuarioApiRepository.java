package com.lp2.sisinventario.sistemainventario.api.repository;

import com.lp2.sisinventario.sistemainventario.api.model.UsuarioApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioApiRepository extends JpaRepository<UsuarioApi, Integer> {
     Optional<UsuarioApi> findByNombre(String nombre);
}
