package edu.alumno.helena.api_rest_bd_futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.alumno.helena.api_rest_bd_futbol.model.db.EmpresaDb;
import jakarta.validation.constraints.NotNull;


@Repository 
public interface EmpresaRepository extends JpaRepository<EmpresaDb, Long> {

    @NotNull
    List<EmpresaDb> findByNombreContaining(String nombre);
    
    @NotNull
    List<EmpresaDb> findAll();
    List<EmpresaDb> findEmpresaInfoById(Long id);
    
   


}