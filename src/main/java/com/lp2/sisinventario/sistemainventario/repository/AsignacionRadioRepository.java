package com.lp2.sisinventario.sistemainventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.lp2.sisinventario.sistemainventario.dto.AsignacionResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.AsignacionRadio;

@Repository
public interface AsignacionRadioRepository extends JpaRepository<AsignacionRadio, Integer> {

    @Query("SELECT new com.lp2.sisinventario.sistemainventario.dto.AsignacionResumenDTO(" +
           "a.id, a.detalleContrato.contrato.numero, a.radioId, a.fechaAsignacion, a.fechaDevolucion, a.estado) " +
           "FROM AsignacionRadio a WHERE a.eliminado = false")
    List<AsignacionResumenDTO> listarAsignacionesResumen();
}