package edu.alumno.helena.dwes_futbol_rest.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //Crea un constructor sin parametros
@AllArgsConstructor //crea un constructor con parametros
@Data //equivale a poner @getter @setter ....
public class CiudadInfo implements Serializable{
    private static final long serialVersionUID=1L;
    private Long id;
    private String nom;
    private String habitants;
}