package edu.alumno.helena.api_rest_bd_pelicula.srv;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;
import io.micrometer.common.lang.NonNull;

public interface PeliculaService {

    //Obtindrer tots els autors
    public List<PeliculaList> findAllPeliculaList();
    public List<PeliculaList> findAllPeliculaList(@NonNull Sort sort);

    //Obtindrer els autors per id
    public PeliculaInfo getPeliculaInfoById(@NonNull Long id);
    public PaginaDto<PeliculaList> findAllPagePeliculaList(@NonNull Pageable pagina);
}