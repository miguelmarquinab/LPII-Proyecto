package com.lp2.sisinventario.sistemainventario.repository;

import org.springframework.stereotype.Repository;

import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.Contrato;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{

	 @Query("SELECT new com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO(" +
	           "    c.id, " +
	           "    c.cliente.id, " +
	           "    c.numero, " +
	           "    c.fechaInicio, " +
	           "    c.fechaFin, " +
	           "    c.valorMensual, " +
	           "    c.estado, " +
	           "    COUNT(d.id), " +
	           "    c.cliente.razonSocial" +
	           ") " +
	           "FROM Contrato c " +
	           "LEFT JOIN c.detalles d " +
	           "WHERE (:estado IS NULL OR c.estado LIKE CONCAT('%', :estado, '%')) " +
	           "  AND (:clienteId IS NULL OR c.cliente.id = :clienteId) " +
	           "  AND (:fechaInicio IS NULL OR c.fechaInicio >= :fechaInicio) " +
	           "  AND (:fechaFin IS NULL OR c.fechaFin <= :fechaFin) " +
	           "GROUP BY c.id, c.cliente.razonSocial") 
		List<ContratoResumenDTO> buscarContratosResumen(
		    @Param("estado") String estado,
		    @Param("clienteId") Integer clienteId,
		    @Param("fechaInicio") LocalDate fechaInicio,
		    @Param("fechaFin") LocalDate fechaFin
		);
	
}
