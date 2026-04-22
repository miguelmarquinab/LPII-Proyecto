package com.lp2.sisinventario.sistemainventario.repository;

import org.springframework.stereotype.Repository;
import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.Contrato;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    @Query("SELECT new com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO(" +
           "    c.id, c.cliente.id, c.numero, c.fechaInicio, c.fechaFin, " +
           "    c.valorMensual, c.estado, COUNT(d.id), c.cliente.razonSocial) " +
           "FROM Contrato c LEFT JOIN c.detalles d " +
           "GROUP BY c.id, c.cliente.id, c.numero, c.fechaInicio, c.fechaFin, c.valorMensual, c.estado, c.cliente.razonSocial")
    List<ContratoResumenDTO> buscarTodosParaResumen();

    @Query("SELECT c FROM Contrato c LEFT JOIN FETCH c.cliente LEFT JOIN FETCH c.detalles WHERE c.id = :id")
    Optional<Contrato> findByIdWithRelation(@Param("id") Integer id);
}