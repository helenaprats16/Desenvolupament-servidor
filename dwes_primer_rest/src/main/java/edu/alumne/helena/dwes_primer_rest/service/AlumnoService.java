package edu.alumne.helena.dwes_primer_rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoEdit;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoInfo;
import edu.alumne.helena.dwes_primer_rest.model.dto.AlumnoList;

public interface AlumnoService {
    public Optional<AlumnoEdit> getAlumnoEditByDni(String dni);

    public Optional<AlumnoInfo> getAlumnoInfoByDni(String dni);

    public AlumnoEdit save(AlumnoEdit alumnoEdit);

    public String deleteByDni(String dni);

    public Optional<AlumnoEdit> update(AlumnoEdit alumnoEdit);

    public List<AlumnoList> findAllAlumnoList();

    // Metodos @Query
    public List<AlumnoList> findAllAlumnoListDAW();

    public List<AlumnoList> findAllAlumnoListDAM();

    public List<AlumnoList> findAllAlumnosListCiclo(String ciclo);

    public List<AlumnoList> findAllAlumnosListCicloPais(String ciclo, String pais);

    // Metodos Spring Data JPA
    public List<AlumnoList> findAllAlumnosListByDni(String dnl);

    public List<AlumnoList> findAllAlumnosListByCiclo(String ciclo);

    public List<AlumnoList> findAllAlumnosListByHorario(String horario);

    public List<AlumnoList> findAllAlumnosListByEdad(Integer edad);

    public List<AlumnoList> findAlumnosListByCicloAndPais(String ciclo, String pais);

    public List<AlumnoList> findAlumnosListByDistintoCiclo(String ciclo, String nombre);

    public List<AlumnoList> findAlumnosListByNombreIgnoreCase(String nombre);

    public List<AlumnoList> findAlumnosListByCicloAndPaisAllIgnoreCase(String ciclo, String pais);

    public List<AlumnoList> findAlumnosListByNombreLike(String nombre);

    public List<AlumnoList>findAllAlumnosListCicloOrderByCurso(String ciclo);

    public List<AlumnoList> findByCicloOrderBy(String ciclo, Sort by);
}
