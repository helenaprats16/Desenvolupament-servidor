package edu.alumno.helena.api_rest_bd_pelicula.srv.mapper;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.DirectorList;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfoNombre;


// Usar Spring para inyección del mapper
@Mapper(componentModel = "spring", uses = {PeliculaMapper.class})
public interface DirectorMapper {

    @Mapping(target = "peliculasInfoNombres", source = "peliculasInfoDb")
    DirectorList directorDbToDirectorList(DirectorDb directorDb);

    @Mapping(target = "peliculasInfoNombres", source = "peliculasInfoDb")
    List<DirectorList> directorsToDirectorList(List<DirectorDb> directorDbList);

    @Mapping(target = "peliculasInfoNombres", source = "peliculasInfoDb")
    DirectorInfo directorDbToDirectorInfo(DirectorDb directorDb);

    DirectorDb directorCreateToDirectorDb(DirectorCreate directorCreate);

    Set<PeliculaInfoNombre> peliculaDbListToPeliculaInfoNombreSet(List<PeliculaDb> peliculasDb);

}