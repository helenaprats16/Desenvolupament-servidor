package org.alumne.helena.primer_app_spring_boot.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.alumne.helena.primer_app_boot.model.ram.Modulo;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ModuloEdit implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@Size(min=5,message="El nombre debe de tener un tamaño mínimo de 5 carácteres")
	private String nombre;
	@Range(min = 2, max = 8, message = "Las horas deben ser igual o mayor a 2 y menor o igual a 8")
	private Integer horas;
	@Size(min = 3,max=3, message = "La abreviatura debe de tener un tamaño de 3 caracteres")
	private String abreviatura;
	
	
	
	
	
	public ModuloEdit(Integer id, String nombre, Integer horas, String abreviatura) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
		this.abreviatura = abreviatura;
	}

	public ModuloEdit() {
		super();
	}

	public boolean contains(ModuloEdit moduloEdit) {
		// TODO Auto-generated method stub
		return false;
	}

	

}

