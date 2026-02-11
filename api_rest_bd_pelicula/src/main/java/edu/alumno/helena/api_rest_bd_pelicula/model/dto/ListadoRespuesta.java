package edu.alumno.helena.api_rest_bd_pelicula.model.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListadoRespuesta<T>{

    int number;//numero de pagina solicitada
    int size;// tamaño de la página
    long totalElements; //total de elementos devueltos por la consulta sin paginación
    int totalPages; //total paginas teniendo en cuenta el tamaño de cada página
    List<T> content; //lista de elementos
}