package edu.alumno.helena.api_rest_bd_pelicula.srv.helper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import edu.alumno.helena.api_rest_bd_pelicula.model.db.DirectorDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.GeneroDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.db.PeliculaDb;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaCreate;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.PeliculaUpdate;

@Component
public class PeliculaEntityFactory {

    public PeliculaDb fromCreate(PeliculaCreate create, DirectorDb director, GeneroDb genero) {
        PeliculaDb peliculaDb = new PeliculaDb();
        peliculaDb.setTitulo(create.getTitulo());
        peliculaDb.setDirector(director);
        peliculaDb.setGenero(genero);
        peliculaDb.setAño(create.getAño());
        peliculaDb.setDuracion(create.getDuracion());
        peliculaDb.setSinopsis(create.getSinopsis());
        peliculaDb.setFechaCreacion(LocalDateTime.now());
        return peliculaDb;
    }

    public void applyUpdate(PeliculaDb peliculaDb, PeliculaUpdate update, DirectorDb director, GeneroDb genero) {
        if (update.getTitulo() != null) {
            peliculaDb.setTitulo(update.getTitulo());
        }
        if (update.getDirectorId() != null) {
            peliculaDb.setDirector(director);
        }
        if (update.getGeneroId() != null) {
            peliculaDb.setGenero(genero);
        }
        if (update.getAño() != null) {
            peliculaDb.setAño(update.getAño());
        }
        if (update.getDuracion() != null) {
            peliculaDb.setDuracion(update.getDuracion());
        }
        if (update.getSinopsis() != null) {
            peliculaDb.setSinopsis(update.getSinopsis());
        }
    }
}
