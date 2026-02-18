package edu.alumno.helena.api_rest_bd_pelicula.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.security.entity.Usuario;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario register(String nombre, String email, String username, String rawPassword) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new EntityAlreadyExistsException("USER_ALREADY_EXISTS", "Ya existe un usuario con email: " + email);
        }
        if (usuarioRepository.existsByUsername(username)) {
            throw new EntityAlreadyExistsException("USER_ALREADY_EXISTS", "Ya existe un usuario con username: " + username);
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        Usuario usuario = new Usuario(null, nombre, email, username, java.time.LocalDateTime.now(), hashedPassword);
        return usuarioRepository.save(usuario);
    }
}
