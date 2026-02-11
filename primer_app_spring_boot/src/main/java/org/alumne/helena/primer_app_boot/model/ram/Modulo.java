package org.alumne.helena.primer_app_boot.model.ram;

public class Modulo implements Comparable<Modulo>{

	private Integer id;
	private String nombre;
	private Integer horas;
	private String abreviatura;
	
	
	
	
	
	
	public Modulo(Integer id, String nombre, Integer horas, String abreviatura) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
		this.abreviatura = abreviatura;
	}
	
	
	
	public Modulo() {
		super();
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}






	@Override
	public int compareTo(Modulo o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
