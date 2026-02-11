package edu.alumno.helena.api_rest_bd_futbol.security.service;

import java.util.Optional;

import org.springframework.lang.NonNull;

import edu.alumno.helena.api_rest_bd_futbol.model.db.RolDb;
import edu.alumno.helena.api_rest_bd_futbol.model.enums.RolNombre;

public interface RolService {
    public Optional<RolDb> getByRolNombre(RolNombre rolNombre);
    public void save(@NonNull RolDb rol);
}


