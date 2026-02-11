package edu.alumno.helena.api_rest_bd_futbol.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.alumno.helena.api_rest_bd_futbol.model.db.EquipoDb;

public interface EquipoRepository extends JpaRepository<EquipoDb, String> {

    


}