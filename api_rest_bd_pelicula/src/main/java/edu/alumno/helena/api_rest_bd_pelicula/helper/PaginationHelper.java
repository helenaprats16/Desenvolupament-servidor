package edu.alumno.helena.api_rest_bd_pelicula.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class PaginationHelper{

    private PaginationHelper(){
        //constructor privado para prevenir instanciacion
    }



    /**
     * Crea un objeto Pageable a partir de los parámetros dados
     * 
     * @param page Número de página (empieza en 0)
     * @param size Tamaño de la página
     * @param sort Array de criterios de ordeancion (ej "cmapo,asc")
     * @return Objeto Pageable con la pagiancion y ordenacion configurada
     * 
     */

    public static Sort createSort(String[] sort, Set<String> allowedFields){
        // Solo construye el Sort; la validacion de page/size se hace en PaginationFactory
        if (sort == null || sort.length == 0) {
            return Sort.unsorted();
        }

        List<Order> criteriosOrdenacion = new ArrayList<Order>();
        if (sort[0].contains(",")) {
            for (String criterioOrdenacion : sort) {
                String[] orden = criterioOrdenacion.split(",");
                if (orden.length > 1) {
                    validateSortField(orden[0], allowedFields);
                    criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]), orden[0]));
                } else {
                    validateSortField(orden[0], allowedFields);
                    criteriosOrdenacion.add(new Order(Direction.fromString("asc"), orden[0]));
                }
            }
        } else {
            validateSortField(sort[0], allowedFields);
            criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]), sort[0]));
        }

        return Sort.by(criteriosOrdenacion);
    }

    private static void validateSortField(String field, Set<String> allowedFields) {
        if (allowedFields == null || allowedFields.isEmpty()) {
            return;
        }
        if (!allowedFields.contains(field)) {
            throw new IllegalArgumentException("Campo sort no valido: " + field);
        }
    }


}