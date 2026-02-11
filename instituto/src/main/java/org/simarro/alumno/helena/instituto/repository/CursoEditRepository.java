package org.simarro.alumno.helena.instituto.repository;

import org.simarro.alumno.helena.instituto.model.db.CursoEditDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CursoEditRepository extends JpaRepository<CursoEditDb, Long>, JpaSpecificationExecutor<CursoEditDb> {
    
}
