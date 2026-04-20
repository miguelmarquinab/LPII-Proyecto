package com.lp2.sisinventario.sistemainventario.api.service.impl;

import com.lp2.sisinventario.sistemainventario.api.dto.AuthRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.AuthResponse;
import com.lp2.sisinventario.sistemainventario.api.security.JwtService;
import com.lp2.sisinventario.sistemainventario.api.service.AuthApiService;
import com.lp2.sisinventario.sistemainventario.api.model.UsuarioApi;
import com.lp2.sisinventario.sistemainventario.api.repository.UsuarioApiRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthApiServiceImpl implements AuthApiService {

    private final UsuarioApiRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthApiServiceImpl(UsuarioApiRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        UsuarioApi usuario = usuarioRepository.findByNombre(request.getUsername())
                .orElseThrow(() -> new RuntimeException("UsuarioApi no encontrado"));

        if (!usuario.getClave().equals(request.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String role = "USER";
        if (usuario.getRol() != null) {
            role = usuario.getRol().getRolNombre();
        }

        String token = jwtService.generateToken(usuario.getNombre(), role);

        return new AuthResponse(token, "Bearer", usuario.getNombre(), role);
    }
}
