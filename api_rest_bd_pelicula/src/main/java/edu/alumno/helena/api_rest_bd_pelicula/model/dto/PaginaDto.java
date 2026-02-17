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
    int size; //tamanyo de la pagina
    long totalElements; //total de elements tornatas per la consulta sense paginacio
    int totalPages; //total paginas tenint en conter el tamany de cada pagina


    List<T> content; //llista d'elements
    Sort sort;




}
