package com.lp2.sisinventario.sistemainventario.repository;

import org.springframework.stereotype.Repository;

import com.lp2.sisinventario.sistemainventario.model.Contrato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{

    @Query(value = """
            SELECT 
                c.con_id AS id,
                cl.cli_id AS clienteId,
                c.con_numero AS numero,
                c.con_fechaInicio AS fechaInicio,
                c.con_fechaFin AS fechaFin,
                c.con_valorMensual AS valorMensual,
                c.con_estado AS estado,
                COALESCE(SUM(dc.dec_cantidad), 0) AS nroRadios,
                cl.cli_razonSocial AS razonSocial
            FROM contratos c
            JOIN clientes cl ON c.cli_id = cl.cli_id
            LEFT JOIN detalleContratos dc ON c.con_id = dc.con_id
            WHERE c.con_estado LIKE CONCAT('%', :estado, '%')
              AND (:clienteId = 0 OR c.cli_id = :clienteId)
              AND (:fechaInicio = '' OR c.con_fechaInicio = STR_TO_DATE(:fechaInicio, '%Y-%m-%d'))
              AND (:fechaFin = '' OR c.con_fechaFin = STR_TO_DATE(:fechaFin, '%Y-%m-%d'))
            GROUP BY c.con_id, cl.cli_id, c.con_numero, c.con_fechaInicio, 
                     c.con_fechaFin, c.con_valorMensual, c.con_estado, cl.cli_razonSocial
        """, nativeQuery = true)
        List<Object[]> buscarContratosResumen(
            @Param("estado") String estado,
            @Param("clienteId") int clienteId,
            @Param("fechaInicio") String fechaInicio,
            @Param("fechaFin") String fechaFin
        );
	
}
