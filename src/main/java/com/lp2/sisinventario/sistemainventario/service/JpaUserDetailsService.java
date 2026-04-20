package com.lp2.sisinventario.sistemainventario.service;

import com.lp2.sisinventario.sistemainventario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = usuarios.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UsuarioApi no encontrado"));

        boolean enabled = u.getEnabled() == null ? true : u.getEnabled();
        var rol = (u.getRol()!=null ? u.getRol().getNombre() : "ROLE_USER");

        log.info("Auth usuario={}, enabled={}, rol={}", u.getUsername(), enabled, rol);

        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .authorities(new SimpleGrantedAuthority(rol))
                .disabled(!enabled)
                .build();
    }
}
