package org.alumne.helena.primer_app_spring_boot.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuloList implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private Integer horas;
	private String abreviatura;
	
	public ModuloList() {
	}
	
}

