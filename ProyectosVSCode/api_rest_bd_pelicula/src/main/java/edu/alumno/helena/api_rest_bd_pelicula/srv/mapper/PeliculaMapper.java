package edu.alumno.helena.api_rest_bd_pelicula.srv.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfo;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaInfoNombre;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaList;

// Usar Spring para inyección del mapper
@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    @Mapping(source = "director.id", target = "directorId")
    @Mapping(source = "director.nombre", target = "directorNombre")
    @Mapping(source = "genero.id", target = "generoId")
    @Mapping(source = "genero.nombre", target = "generoNombre")
    PeliculaList peliculaDbToPeliculaList(PeliculaDb peliculaDb);

    @Mapping(source = "director.id", target = "directorId")
    @Mapping(source = "director.nombre", target = "directorNombre")
    @Mapping(source = "genero.id", target = "generoId")
    @Mapping(source = "genero.nombre", target = "generoNombre")
    PeliculaInfo peliculaDbToPeliculaInfo(PeliculaDb peliculaDb);
    PeliculaInfoNombre peliculaDbToPeliculaInfoNombre(PeliculaDb peliculaDb);

    List<PeliculaList> peliculasToPeliculaList(List<PeliculaDb> peliculaDbList);
    


    
}