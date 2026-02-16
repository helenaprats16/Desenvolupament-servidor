package edu.alumno.helena.api_rest_bd_pelicula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.alumno.helena.api_rest_bd_pelicula.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
            // API sin sesiones, solo token
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Login y documentacion Swagger abiertos
                .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**").permitAll()
                // GET libres (consultas publicas)
                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                // POST/PUT/DELETE de peliculas, directores y generos requieren JWT
                .requestMatchers(HttpMethod.POST, "/api/v1/peliculas", "/api/v1/directores", "/api/v1/generos").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/v1/peliculas/**", "/api/v1/directores/**", "/api/v1/generos/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/peliculas/**", "/api/v1/directores/**", "/api/v1/generos/**").authenticated()
                // Todo lo demas es publico
                .anyRequest().permitAll()
            )
            // JWT antes del filtro de usuario/clave
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
