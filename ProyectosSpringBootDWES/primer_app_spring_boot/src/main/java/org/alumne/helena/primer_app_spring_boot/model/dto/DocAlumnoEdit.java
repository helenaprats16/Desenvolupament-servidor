package org.alumne.helena.primer_app_spring_boot.model.dto;

import java.util.Objects;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocAlumnoEdit implements Comparable<DocAlumnoEdit>{
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	private String dni;
	//No ponemos restricciones al "id" porque vale la pena que este vacio y que 
	//cuando se intente añadir el documento el servicio calcule el siguiente id para ese dni
	private Integer id;
	@NotNull(message = "El tipo no puede estar vacio")
	private String tipo;
	@Size(min = 10, message = "Los comentarios debe tener almenos 10 carácteres")
	private String comentario;
	
	
		
	@Override
	public int hashCode() {
		return Objects.hash(dni, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocAlumnoEdit other = (DocAlumnoEdit) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(id, other.id);
	}
	public DocAlumnoEdit() {
		super();
	}
	public DocAlumnoEdit(Integer id) {
		super();
		this.id = id;
	}
	@Override
	public int compareTo(DocAlumnoEdit doc) {
		return id-doc.getId();
	}
	
}	


