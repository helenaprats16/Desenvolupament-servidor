package edu.alumno.helena.dwes_futbol_rest.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Sort;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginaDto<T> {

    int number;//num de pagina solicitada
    int size; //tamaño de la pagina
    long totalElements; //total de elementos devueltos por la consulta sin paginacion
    int totalPages; //total paginas teniendo en cuenta el tamaño de cada pagina


    List<T> content; //lista de elementos
    Sort sort;



}
