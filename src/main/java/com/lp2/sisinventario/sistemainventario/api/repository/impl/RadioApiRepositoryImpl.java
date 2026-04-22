package com.lp2.sisinventario.sistemainventario.api.repository.impl;

import com.lp2.sisinventario.sistemainventario.api.dto.CatalogoItemResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioResponse;
import com.lp2.sisinventario.sistemainventario.api.repository.RadioApiRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RadioApiRepositoryImpl implements RadioApiRepository {

    private final JdbcTemplate jdbcTemplate;

    public RadioApiRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RadioResponse> listar(String texto) {
        return jdbcTemplate.query(
                "CALL usp_Buscar_Radio(?)",
                new Object[]{texto},
                (rs, rowNum) -> {
                    RadioResponse item = new RadioResponse();
                    item.setId(rs.getInt("rad_id"));
                    item.setModCodigo(rs.getString("mod_codigo"));
                    item.setModelo(rs.getString("modelo"));
                    item.setEstado(rs.getString("estado"));
                    item.setSerie(rs.getString("serie"));
                    item.setFechaIngreso(rs.getString("fecha_ingreso"));
                    item.setActivo(rs.getObject("rad_activo") != null ? rs.getInt("rad_activo") : null);
                    return item;
                }
        );
    }

    @Override
    public RadioResponse obtener(Integer id) {
        List<RadioResponse> lista = jdbcTemplate.query(
                "CALL usp_radio_obtener(?)",
                new Object[]{id},
                (rs, rowNum) -> {
                    RadioResponse item = new RadioResponse();
                    item.setId(rs.getInt("radio_id"));
                    item.setModId(rs.getInt("mod_id"));
                    item.setEsrId(rs.getInt("esr_id"));
                    item.setModCodigo(rs.getString("mod_codigo"));
                    item.setModelo(rs.getString("modelo"));
                    item.setEstado(rs.getString("estado"));
                    item.setSerie(rs.getString("serie"));
                    item.setFechaIngreso(rs.getString("fecha_ingreso"));
                    item.setActivo(rs.getObject("rad_activo") != null ? rs.getInt("rad_activo") : null);
                    return item;
                }
        );

        if (lista.isEmpty()) {
            throw new RuntimeException("Radio no encontrado");
        }

        return lista.get(0);
    }

    @Override
    public Integer guardar(RadioRequest request) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("usp_radio_guardar");

        LocalDateTime fecha = LocalDateTime.parse(
                request.getFechaIngreso(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_rad_id", request.getId());
        inParams.put("p_mod_id", request.getModId());
        inParams.put("p_esr_id", request.getEsrId());
        inParams.put("p_serie", request.getSerie());
        inParams.put("p_fecha_ingreso", Timestamp.valueOf(fecha));
        inParams.put("p_activo", request.getActivo());

        Map<String, Object> out = call.execute(inParams);

        Object value = out.get("nuevo_id");
        if (value instanceof Number number) {
            return number.intValue();
        }

        List<?> rsList = (List<?>) out.get("#result-set-1");
        if (rsList != null && !rsList.isEmpty()) {
            Object first = rsList.get(0);
            if (first instanceof Map<?, ?> row) {
                Object nuevoId = row.get("nuevo_id");
                if (nuevoId instanceof Number number) {
                    return number.intValue();
                }
            }
        }

        throw new RuntimeException("No se pudo obtener el id generado");
    }

    @Override
    public void eliminar(Integer id) {
        jdbcTemplate.update("CALL usp_radio_eliminar(?)", id);
    }

    @Override
    public List<CatalogoItemResponse> listarModelos() {
        return jdbcTemplate.query(
                "CALL usp_radio_modelos()",
                (rs, rowNum) -> new CatalogoItemResponse(
                        rs.getInt("mod_id"),
                        rs.getString("mod_codigo"),
                        rs.getString("mod_descripcion")
                )
        );
    }

    @Override
    public List<CatalogoItemResponse> listarEstados() {
        return jdbcTemplate.query(
                "CALL usp_radio_estados()",
                (rs, rowNum) -> new CatalogoItemResponse(
                        rs.getInt("esr_id"),
                        null,
                        rs.getString("esr_descripcion")
                )
        );
    }
}
