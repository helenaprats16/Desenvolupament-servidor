package edu.alumno.helena.api_rest_bd_pelicula.helper;

import lombok.Data;

@Data
public class PaginationRequest {

    private final int page;
    private final int size;
    private final String[] sort;

    public PaginationRequest(int page, int size, String[] sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String[] getSort() {
        return sort;
    }
}
