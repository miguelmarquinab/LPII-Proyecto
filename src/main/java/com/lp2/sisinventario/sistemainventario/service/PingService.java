package com.lp2.sisinventario.sistemainventario.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    private final JdbcTemplate jdbcTemplate;

    public PingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean ping() {
        try {
            // Retorna 1 si hay conexión OK
            Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return one != null && one == 1;
        } catch (Exception ex) {
            return false;
        }
    }
}
