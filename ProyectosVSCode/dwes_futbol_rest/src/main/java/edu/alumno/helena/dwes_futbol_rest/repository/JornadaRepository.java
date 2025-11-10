package edu.alumno.helena.dwes_futbol_rest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.dwes_futbol_rest.model.db.JornadaDb;


@Repository
public interface JornadaRepository extends JpaRepository<JornadaDb, Long> {

    //Métodos automáticos en Spring Data JPA
    @NonNull List<JornadaDb> findAll(@NonNull Sort sort);
    @NonNull List<JornadaDb> findByNumContaining(@NonNull String fecha, @NonNull Pageable paging);
    List<JornadaDb> findByFechaContaining(String fecha, Sort sort);

    // Versiones paginadas
    Page<JornadaDb> findByNumContaining(@NonNull Long num, @NonNull Pageable pageable);
    Page<JornadaDb> findByFechaContaining(String fecha, Pageable pageable);


    

}