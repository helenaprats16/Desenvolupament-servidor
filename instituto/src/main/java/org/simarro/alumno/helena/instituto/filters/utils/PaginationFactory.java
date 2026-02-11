package org.simarro.alumno.helena.instituto.filters.utils;

import org.simarro.alumno.helena.instituto.filters.model.PeticionListadoFiltrado;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationFactory {

    public Pageable createPageable(PeticionListadoFiltrado peticion) {

        if (peticion.getSortArray() == null || peticion.getSortArray().length == 0) {
            return PageRequest.of(peticion.getPage(), peticion.getSize());
        }

        String[] sort = peticion.getSortArray();

        Sort.Direction direction = Sort.Direction.ASC;
        if (sort.length > 1) {
            direction = Sort.Direction.fromString(sort[1]);
        }

        return PageRequest.of(
            peticion.getPage(),
            peticion.getSize(),
            Sort.by(direction, sort[0])
        );
    }
}
