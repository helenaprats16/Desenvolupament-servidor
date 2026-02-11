package edu.alumno.helena.api_rest_bd_pelicula.srv.helper;

import org.springframework.stereotype.Component;

import edu.alumno.helena.api_rest_bd_pelicula.exception.DirectorNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.repository.DirectorRepository;

@Component
public class DirectorDependencyResolver {

    private final DirectorRepository directorRepository;

    public DirectorDependencyResolver(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public DirectorDb requireDirector(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("DIRECTOR_NOT_FOUND", "Director no encontrado: " + id));
    }
}
