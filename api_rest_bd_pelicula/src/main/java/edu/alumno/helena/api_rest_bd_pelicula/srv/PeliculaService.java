package edu.alumno.helena.api_rest_bd_pelicula.srv;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginaResponse;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorAño;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculasPorGenero;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaUpdate;
import io.micrometer.common.lang.NonNull;

public interface PeliculaService {

    //Obtindrer tots les pelicules
    public List<PeliculaList> findAllPeliculaList();
    public List<PeliculaList> findAllPeliculaList(@NonNull Sort sort);

    //Obtindrer totes les pelicules per id
    public PeliculaInfo getPeliculaInfoById(@NonNull Long id);
    public PaginaDto<PeliculaList> findAllPagePeliculaList(@NonNull Pageable pagina);

    public PaginaResponse<PeliculaList> findAll(String[] filter, int page, int size, String[] sort);
    public PaginaResponse<PeliculaList> findAll(PeticionListadoFiltrado peticionListadoFiltrado);

    public PaginaDto<PeliculaList> findByNombreContaining(String nombre, Pageable pageable);
    public List<PeliculaList> findPeliculasByDirector(Long directorId);
    public List<PeliculaList> findPeliculasFromYear(Integer year);

    public PeliculaInfo createPelicula(PeliculaCreate peliculaCreate);
    public PeliculaInfo updatePelicula(Long id, PeliculaUpdate peliculaUpdate);
    public void deletePeliculaById(@NonNull Long id);
    
    // Consultas agrupadas para estadísticas
    public List<PeliculasPorAño> getPeliculasAgrupadasPorAño();
    public List<PeliculasPorGenero> getPeliculasAgrupadasPorGenero();
}