package edu.alumne.helena.dwes_primer_rest.model.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlumnoList implements Serializable{
	private static final long serialVersionUID = 1L;
	private String dni;
	private String nombre;
	private Integer edad;
	private String ciclo;
	private Integer curso;
	private boolean erasmus=false;
	
	public String getErasmusChecked() {
		if (erasmus)
				return "checked";
		else
			return "";
	}
}






