package org.alumne.helena.primer_app_boot.model.ram;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Alumno implements Serializable,Comparable<Alumno>{

	private static final long serialVersionUID = 1L;
    @Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
    private String dni;
    @Size(min=5,message= "El nombre debe de tener un tamaño mínimo de 5 carácterés")
    private String nombre;
    @NotNull(message = "La edad no puede estar vacía")
    @Range(min = 18, max = 99, message = "La edad debe ser igual o mayor a 18 y menor o igual a 99")
    @Digits(integer = 2,fraction = 0, message = "La edad no puede tener decimales ni más de 2 dígitos")
    private Integer edad;
    @Size(min = 3, message = "El ciclo debe tener almenos 3 carácterés")
    private String ciclo;
    @NotNull(message = "El curso no puede estar vacio")
    @Digits(fraction = 0, integer = 1,message = "El curso tiene un formato incorrecto")
    @Range(min = 1, max = 2, message = "El curso solo admite los valores 1 o 2")
    private Integer curso;

	
	public Alumno(String dni, String nombre, Integer edad, String ciclo, Integer curso) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.ciclo = ciclo;
		this.curso = curso;
	}
	
	public Alumno() {
		
	}
	
	
	
	
	
	public Alumno(String dni) {
		super();
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Alumno [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + ", ciclo=" + ciclo + ", curso=" + curso
				+ "]";
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int compareTo(Alumno o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
	
	


}
