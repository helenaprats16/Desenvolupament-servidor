package edu.alumno.helena.api_rest_bd_pelicula.security.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_pelicula.security.entity.Rol;
import edu.alumno.helena.api_rest_bd_pelicula.security.entity.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombre(RolNombre nombre);
}
