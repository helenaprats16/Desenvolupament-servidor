package edu.alumno.helena.api_rest_bd_futbol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.alumno.helena.api_rest_bd_futbol.model.db.UsuarioDb;

//Buscar per el nickname y saber si un nickname o un email existe
public interface UsuarioRepository extends JpaRepository<UsuarioDb,Long>{
    Optional<UsuarioDb> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}