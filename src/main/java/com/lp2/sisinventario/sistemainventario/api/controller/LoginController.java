package com.lp2.sisinventario.sistemainventario.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.lp2.sisinventario.sistemainventario.model.Usuario;
import com.lp2.sisinventario.sistemainventario.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor 
public class LoginController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        
        Usuario user = usuarioRepository.findByUsername(usuario.getUsername()).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}