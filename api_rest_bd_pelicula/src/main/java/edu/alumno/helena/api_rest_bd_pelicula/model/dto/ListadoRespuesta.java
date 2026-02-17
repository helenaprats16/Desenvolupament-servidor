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
    int size;// tamanyo de la pàgina
    long totalElements; //total de elements tornats per la consutla sense paginacion
    int totalPages; //total pagines tenint en conter el tamany de cada pàgina
    List<T> content; //llista d'elements
}