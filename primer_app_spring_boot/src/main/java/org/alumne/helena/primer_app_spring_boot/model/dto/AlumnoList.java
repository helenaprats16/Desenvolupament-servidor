package org.alumne.helena.primer_app_spring_boot.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoList implements Serializable{
	private static final long serialVersionUID = 1L;
	private String dni;
	private String nombre;
	private Integer edad;
	private String ciclo;
	private Integer curso;
	
	public AlumnoList() {
	}
	
}

