package edu.alumno.helena.api_rest_bd_futbol.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import edu.alumno.helena.api_rest_bd_futbol.model.dto.EquipoInfoNombre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //Crea un constructor sin parametros
@AllArgsConstructor //crea un constructor con parametros
@Data //equivale a poner @getter @setter ....
public class CiudadInfo implements Serializable{
    private static final long serialVersionUID=1L;
    private Long id;
    private String nombre;
    private String habitantes;
    private Set<EquipoInfoNombre> equiposInfoNombres = new HashSet<>();
}