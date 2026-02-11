package edu.alumne.helena.dwes_primer_rest.model.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlumnoInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String dni_alumno;
	private String nombre_alumno;
	private String ciclo_alumno;
	private Integer curso_alumno;
}

