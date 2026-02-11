package edu.alumno.helena.api_rest_bd_pelicula.helper;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.alumno.helena.api_rest_bd_pelicula.exception.BadRequestException;

@Component
public class PaginationFactory {

    public Pageable createPageable(PaginationRequest request, Set<String> allowedFields) {
        int page = request.getPage();
        int size = request.getSize();

        if (page < 0) {
            throw new BadRequestException("PAGINATION_INVALID", "El parametro page no puede ser negativo");
        }
        if (size <= 0) {
            throw new BadRequestException("PAGINATION_INVALID", "El parametro size debe ser mayor que 0");
        }

        String[] sort = request.getSort();
        if (sort == null || sort.length == 0) {
            return PageRequest.of(page, size);
        }

        try {
            return PageRequest.of(page, size, PaginationHelper.createSort(sort, allowedFields));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("SORT_FIELD_INVALID", ex.getMessage());
        }
    }
}
