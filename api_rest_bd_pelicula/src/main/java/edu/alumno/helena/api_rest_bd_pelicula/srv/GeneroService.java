package edu.alumno.helena.api_rest_bd_pelicula.srv;

import org.springframework.data.domain.Pageable;

import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.GeneroUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;

public interface GeneroService {
    
    public PaginaDto<GeneroInfo> findAllPageGeneros(Pageable pagina);
    public PaginaDto<GeneroInfo> findByNombreContaining(String nombre, Pageable pagina);
    public GeneroInfo getGeneroInfoById(Long id);
    public GeneroInfo createGenero(GeneroCreate generoCreate);
    public GeneroInfo updateGenero(Long id, GeneroUpdate generoUpdate);
    void deleteGeneroById(Long id);
}
