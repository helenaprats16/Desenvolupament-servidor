package edu.alumno.helena.api_rest_bd_pelicula.security.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.alumno.helena.api_rest_bd_pelicula.exception.EntityAlreadyExistsException;
import edu.alumno.helena.api_rest_bd_pelicula.security.entity.Rol;
import edu.alumno.helena.api_rest_bd_pelicula.security.entity.RolNombre;
import edu.alumno.helena.api_rest_bd_pelicula.security.entity.Usuario;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario register(String nombre, String email, String username, String rawPassword) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new EntityAlreadyExistsException("USER_ALREADY_EXISTS", "Ya existe un usuario con email: " + email);
        }
        if (usuarioRepository.existsByUsername(username)) {
            throw new EntityAlreadyExistsException("USER_ALREADY_EXISTS", "Ya existe un usuario con username: " + username);
        }

        Rol rolUser = rolRepository.findByNombre(RolNombre.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("No existe el rol ROLE_USER en la base de datos"));

        String hashedPassword = passwordEncoder.encode(rawPassword);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setUsername(username);
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setPassword(hashedPassword);
        usuario.setRoles(Set.of(rolUser));

        return usuarioRepository.save(usuario);
    }
}
