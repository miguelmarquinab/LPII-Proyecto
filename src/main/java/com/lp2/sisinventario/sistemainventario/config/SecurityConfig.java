//package com.lp2.sisinventario.sistemainventario.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/login",
//                                "/api/auth/**",
//                                "/api/radios/**", // (si quieres pruebas rápidas)
//
//                                "/v3/api-docs/**",
//                                "/swagger-ui/**",
//                                "/swagger-ui.html",
//                                "/swagger-ui/index.html",
//                                "/swagger-resources/**",
//                                "/webjars/**",
//
//                                "/css/**",
//                                "/js/**",
//                                "/images/**",
//                                "/actuator/**"
//                        ).permitAll()
//                        .requestMatchers("/", "/dashboard", "/modelos/**").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/dashboard", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
////                .csrf(csrf -> csrf.ignoringRequestMatchers("/actuator/**"))
////                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/actuator/**", "/api/**"));
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        // SOLO PARA DESARROLLO: no en producción
//        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(
//            UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        var provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }
//}
package com.lp2.sisinventario.sistemainventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.lp2.sisinventario.sistemainventario.api.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/api/auth/**",
                                "/api/radios/**",

                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/swagger-resources/**",
                                "/webjars/**",

                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/actuator/**"
                        ).permitAll()
                        .requestMatchers("/", "/dashboard", "/modelos/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )

                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/actuator/**"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        http.addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}