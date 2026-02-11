package edu.alumno.helena.api_rest_bd_pelicula.model.dto;
import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
