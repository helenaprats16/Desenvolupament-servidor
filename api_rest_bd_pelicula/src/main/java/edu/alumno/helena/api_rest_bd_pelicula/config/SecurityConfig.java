package edu.alumno.helena.api_rest_bd_pelicula.config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.alumno.helena.api_rest_bd_pelicula.exception.ApiError;
import edu.alumno.helena.api_rest_bd_pelicula.security.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter, ObjectMapper objectMapper) throws Exception {
        http
            // API sense sessions, sols token
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) ->
                    writeSecurityError(response, request, HttpStatus.UNAUTHORIZED,
                        "AUTHENTICATION_REQUIRED", "Autenticació requerida", objectMapper))
                .accessDeniedHandler((request, response, accessDeniedException) ->
                    writeSecurityError(response, request, HttpStatus.FORBIDDEN,
                        "ACCESS_DENIED", "No tens permisos per accedir a aquest recurs", objectMapper))
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**").permitAll()
                // Qualsevol usuari (inclòs no autenticat) pot fer GET
                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                // ROLE_USER i ROLE_ADMIN poden fer PUT
                .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("USER", "ADMIN")
                // Només ROLE_ADMIN pot fer POST i DELETE
                .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                .anyRequest().denyAll()
            )
            // JWT antes del filtre de usuari/clave
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

    private void writeSecurityError(HttpServletResponse response, HttpServletRequest request,
            HttpStatus status, String errorCode, String message, ObjectMapper objectMapper) throws IOException {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                errorCode,
                message,
                request.getRequestURI(),
                null);

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
