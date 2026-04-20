package com.lp2.sisinventario.sistemainventario.api.controller;

import com.lp2.sisinventario.sistemainventario.api.dto.ApiResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.AuthRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.AuthResponse;
import com.lp2.sisinventario.sistemainventario.api.service.AuthApiService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final AuthApiService authApiService;

    public AuthApiController(AuthApiService authApiService) {
        this.authApiService = authApiService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        AuthResponse response = authApiService.login(request);
        return ResponseEntity.ok(ApiResponse.ok("Autenticación correcta", response));
    }
}
