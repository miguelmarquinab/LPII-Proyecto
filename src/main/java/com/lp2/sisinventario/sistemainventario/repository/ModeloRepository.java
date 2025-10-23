package com.lp2.sisinventario.sistemainventario.repository;

import com.lp2.sisinventario.sistemainventario.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ModeloRepository extends JpaRepository<Modelo, Integer>{
    boolean existsByCodigoIgnoreCase(String codigo);
}
