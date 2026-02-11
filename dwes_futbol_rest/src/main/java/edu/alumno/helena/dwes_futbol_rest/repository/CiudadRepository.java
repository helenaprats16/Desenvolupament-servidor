package edu.alumno.helena.dwes_futbol_rest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.dwes_futbol_rest.model.db.CiudadDb;

@Repository //per indicarli que es un repositori 
public interface CiudadRepository extends JpaRepository<CiudadDb, Long> {

    //Métodos automáticos en Spring Data JPA
    //ListaPagingAndSortingRepository<CiudadDb, Long> specifies parameter and return as @NonNull
    @NonNull 
    List<CiudadDb> findAll(@NonNull Sort sort);
    //Filtrado por nombre usando equivalente a 'LIKE' usando "Containing"
    @NonNull List<CiudadDb> findByNombreContaining(@NonNull String nombre, @NonNull Sort sort);
    List<CiudadDb> findByNombreContainingIgnoreCase(String palabra, Sort sort);

    // Versiones paginadas
    Page<CiudadDb> findByNombreContaining(@NonNull String nombre, @NonNull Pageable pageable);
    Page<CiudadDb> findByNombreContainingIgnoreCase(String palabra, Pageable pageable);



}