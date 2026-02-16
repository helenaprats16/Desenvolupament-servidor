package edu.alumno.helena.api_rest_bd_pelicula.helper;

import org.springframework.stereotype.Component;

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
