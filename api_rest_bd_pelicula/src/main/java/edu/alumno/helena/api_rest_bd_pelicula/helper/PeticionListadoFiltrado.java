package edu.alumno.helena.api_rest_bd_pelicula.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeticionListadoFiltrado {

    private String[] filter;
    private int page;
    private int size;
    private String[] sort;

    public PeticionListadoFiltrado() {}

    public PeticionListadoFiltrado(String[] filter, int page, int size, String[] sort) {
        this.filter = filter;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    // ----------- GETTERS/SETTERS de arrays (per converter i paginationFactory) -----------
    public String[] getFilter() {
        return filter;
    }

    public void setFilter(String[] filter) {
        this.filter = filter;
    }

    public String[] getSortArray() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }

    // ----------- GETTERS que retorna llistes (per service i mapper) -----------
    public List<FiltroBusqueda> getListaFiltros() {
        if (filter == null || filter.length == 0) {
            return List.of();
        }

        return Arrays.stream(filter)
                .map(FiltroBusqueda::new)
                .filter(FiltroBusqueda::esValido)
                .collect(Collectors.toList());
    }

    public List<String> getSort() {
        if (sort == null || sort.length == 0) {
            return List.of();
        }
        return Arrays.asList(sort);
    }

    // ----------- page i size -----------
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
