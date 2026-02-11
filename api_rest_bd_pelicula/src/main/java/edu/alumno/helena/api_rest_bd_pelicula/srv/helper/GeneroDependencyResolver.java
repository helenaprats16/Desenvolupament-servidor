package edu.alumno.helena.api_rest_bd_pelicula.srv.helper;

import org.springframework.stereotype.Component;

import edu.alumno.helena.api_rest_bd_pelicula.exception.GeneroNotFoundException;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.repository.GeneroRepository;

@Component
public class GeneroDependencyResolver {

    private final GeneroRepository generoRepository;

    public GeneroDependencyResolver(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public GeneroDb requireGenero(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new GeneroNotFoundException("GENERO_NOT_FOUND", "Genero no encontrado: " + id));
    }
}
