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
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        boolean enabled = u.getEnabled() == null ? true : u.getEnabled();
        String rol = "ROLE_USER";

        if (u.getRol() != null && u.getRol().getNombre() != null) {
            rol = u.getRol().getNombre();
        }

        log.info("Auth usuario={}, passwordBD={}, enabled={}, rol={}",
                u.getUsername(), u.getPassword(), enabled, rol);

        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(rol)))
                .disabled(!enabled)
                .build();
    }
}
