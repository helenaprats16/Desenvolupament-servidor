package edu.alumno.helena.api_rest_bd_pelicula.helper;

import org.springframework.stereotype.Component;

import edu.alumno.helena.api_rest_bd_pelicula.exception.DirectorNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.exception.GeneroNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.repository.DirectorRepository;
import edu.alumno.helena.api_rest_bd_pelicula.repository.GeneroRepository;

@Component
public class PeliculaDependencyResolver {

    private final DirectorRepository directorRepository;
    private final GeneroRepository generoRepository;

    public PeliculaDependencyResolver(DirectorRepository directorRepository, GeneroRepository generoRepository) {
        this.directorRepository = directorRepository;
        this.generoRepository = generoRepository;
    }

    public DirectorDb requireDirector(Long directorId) {
        return directorRepository.findById(directorId)
                .orElseThrow(() -> new DirectorNotFoundException("DIRECTOR_NOT_FOUND",
                        "Director no encontrado: " + directorId));
    }

    public GeneroDb requireGenero(Long generoId) {
        return generoRepository.findById(generoId)
                .orElseThrow(() -> new GeneroNotFoundException("GENERO_NOT_FOUND",
                        "Genero no encontrado: " + generoId));
    }

    public DirectorDb optionalDirector(Long directorId) {
        if (directorId == null) {
            return null;
        }
        return requireDirector(directorId);
    }

    public GeneroDb optionalGenero(Long generoId) {
        if (generoId == null) {
            return null;
        }
        return requireGenero(generoId);
    }
}
