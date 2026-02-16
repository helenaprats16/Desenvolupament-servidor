package edu.alumno.helena.api_rest_bd_pelicula.srv;

import org.springframework.data.domain.Pageable;

import edu.alumno.helena.api_rest_bd_pelicula.helper.PaginaResponse;
import edu.alumno.helena.api_rest_bd_pelicula.helper.PeticionListadoFiltrado;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;

public interface GeneroService {
    
    public PaginaDto<GeneroInfo> findAllPageGeneros(Pageable pagina);
    public PaginaResponse<GeneroInfo> findAll(String[] filter, int page, int size, String[] sort);
    public PaginaResponse<GeneroInfo> findAll(PeticionListadoFiltrado peticionListadoFiltrado);
    public PaginaDto<GeneroInfo> findByNombreContaining(String nombre, Pageable pagina);
    public GeneroInfo getGeneroInfoById(Long id);
    public GeneroInfo createGenero(GeneroCreate generoCreate);
    public GeneroInfo updateGenero(Long id, GeneroUpdate generoUpdate);
    void deleteGeneroById(Long id);
}
