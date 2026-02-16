package edu.alumno.helena.api_rest_bd_pelicula.helper;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginaResponse<T> {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<T> content;
    private List<FiltroBusqueda> filtros;
    private List<String> ordenaciones;

    public PaginaResponse(int page, int size, long totalElements, int totalPages,
                          List<T> content, List<FiltroBusqueda> filtros, List<String> ordenaciones) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.content = content;
        this.filtros = filtros;
        this.ordenaciones = ordenaciones;
    }

    // getters y setters...
}
