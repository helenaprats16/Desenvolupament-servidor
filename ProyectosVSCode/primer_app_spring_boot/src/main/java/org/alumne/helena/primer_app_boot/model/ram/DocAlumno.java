package org.alumne.helena.primer_app_boot.model.ram;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DocAlumno implements Comparable<DocAlumno> {

	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	private String dni;
	//No ponemos restricciones al "id" porque vale la pena que este vacio y que
	//cuando se intente añadir el documento el servicio calcule el siguiente id para ese dni
	
	private Integer id;
	@NotNull(message = "El tipo no puede estar vacio")
	private String tipo;
	@Size(min = 10, message="Los comentarios debe tener almenos 10 carácteres")
	private String comentario;
	
	
	
		
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(comentario, dni, id, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocAlumno other = (DocAlumno) obj;
		return Objects.equals(comentario, other.comentario) && Objects.equals(dni, other.dni)
				&& Objects.equals(id, other.id) && Objects.equals(tipo, other.tipo);
	}

	public DocAlumno() {
		super();
	}
	public DocAlumno(Integer id) {
		super();
		this.id = id;
	}
	@Override
	public int compareTo(DocAlumno arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
