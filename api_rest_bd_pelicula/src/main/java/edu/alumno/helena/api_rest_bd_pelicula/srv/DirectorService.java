package edu.alumno.helena.api_rest_bd_pelicula.srv;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorUpdate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PaginaDto;
import io.micrometer.common.lang.NonNull;

public interface DirectorService {

    //Obtindrer tots els autors
    public List<DirectorList> findAllDirectorList();
    public List<DirectorList> findAllDirectorList(@NonNull Sort sort);

    //Obtindrer els autors per id
    public DirectorInfo getDirectorInfoById(@NonNull Long id);
    public PaginaDto<DirectorList> findAllPageDirectorList(@NonNull Pageable pagina);
    public PaginaDto<DirectorList> findByNombreContaining(@NonNull String nombre, @NonNull Pageable pagina);
    public DirectorInfo createDirector(DirectorCreate directorCreate);
    public DirectorInfo updateDirector(Long id, DirectorUpdate directorUpdate);
    public void deleteDirectorById(@NonNull Long id);
}