package edu.alumno.helena.api_rest_bd_pelicula.helper;

import org.springframework.stereotype.Component;

@Component
public class PaginationRequestConverter {

    public PaginationRequest fromParams(int page, int size, String[] sort) {
        return new PaginationRequest(page, size, sort);
    }
}
