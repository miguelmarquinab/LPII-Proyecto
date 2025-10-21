package com.lp2.sisinventario.sistemainventario.controller;

import com.lp2.sisinventario.sistemainventario.service.PingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/ping-db")
    public ResponseEntity<?> pingDb() {
        boolean ok = pingService.ping();
        return ResponseEntity.ok(new PingResponse(ok ? "UP" : "DOWN", OffsetDateTime.now().toString()));
    }

    static class PingResponse {
        public final String status;
        public final String timestamp;
        PingResponse(String status, String timestamp) {
            this.status = status;
            this.timestamp = timestamp;
        }
    }
}
