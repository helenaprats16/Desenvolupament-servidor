package edu.alumno.helena.api_rest_bd_pelicula.security.utils;

import org.springframework.stereotype.Component;

import edu.alumno.helena.api_rest_bd_pelicula.security.PeticionListadoFiltrado;

@Component
public class PeticionListadoFiltradoConverter {

    public PeticionListadoFiltrado convertFromParams(
            String[] filter, int page, int size, String[] sort) {

        PeticionListadoFiltrado peticion = new PeticionListadoFiltrado();
        peticion.setFilter(filter);
        peticion.setPage(page);
        peticion.setSize(size);
        peticion.setSort(sort);
        return peticion;
    }
}
