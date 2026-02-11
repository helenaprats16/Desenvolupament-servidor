package edu.alumno.helena.api_rest_bd_pelicula.srv;

import org.springframework.data.domain.Pageable;

import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;

public interface GeneroService {

    PaginaDto<GeneroInfo> findAllPageGeneros(Pageable pagina);
    PaginaDto<GeneroInfo> findByNombreContaining(String nombre, Pageable pagina);
    GeneroInfo getGeneroInfoById(Long id);
    GeneroInfo createGenero(GeneroCreate generoCreate);
    GeneroInfo updateGenero(Long id, GeneroUpdate generoUpdate);
    void deleteGeneroById(Long id);
}
