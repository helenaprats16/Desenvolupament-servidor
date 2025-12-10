package edu.alumno.helena.api_rest_bd_futbol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.alumno.helena.api_rest_bd_futbol.model.db.RolDb;
import edu.alumno.helena.api_rest_bd_futbol.model.enums.RolNombre;

//buscar per un nickname o un email existe
public interface RolRepository extends JpaRepository<RolDb,Integer>{
    Optional<RolDb> findByNombre(RolNombre rolNombre);
}