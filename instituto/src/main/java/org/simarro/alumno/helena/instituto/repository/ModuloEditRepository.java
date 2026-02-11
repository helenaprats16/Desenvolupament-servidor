package org.simarro.alumno.helena.instituto.repository;

import org.simarro.alumno.helena.instituto.model.db.ModuloEditDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ModuloEditRepository extends JpaRepository<ModuloEditDb, Long>, JpaSpecificationExecutor<ModuloEditDb> {
    
}