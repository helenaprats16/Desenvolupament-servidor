package org.alumne.helena.primer_app_spring_boot.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumne.helena.primer_app_boot.model.ram.Alumno;
import org.alumne.helena.primer_app_boot.model.ram.DocAlumno;
import org.alumne.helena.primer_app_boot.model.ram.Modulo;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.ModuloEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.ModuloList;
import org.alumne.helena.primer_app_spring_boot.srv.mapper.AlumnoMapper;
import org.alumne.helena.primer_app_spring_boot.srv.mapper.ModuloMapper;
import org.springframework.stereotype.Service;

@Service
public class ModuloService {
	private static List<Modulo> modulos  = new ArrayList<Modulo>();
	static {
		modulos.add(new Modulo(1, "Programacion",8,"PRO"));
		modulos.add(new Modulo(2, "Desarrollo Web en Entorno Servidor",8,"DWES"));

	}
	
	
	
	public List<ModuloList> listaModulos(){

		return ModuloMapper.INSTANCE.moduloToLisModuloList(modulos);
	}
	
	
	//crear metodo para añadir un modulo al servicio
	public void addModulo(ModuloEdit moduloEdit) throws Exception{
		
		modulos.add(new Modulo(siguienteId(),moduloEdit.getNombre(),moduloEdit.getHoras(),moduloEdit.getAbreviatura()));
		
	}
		
	private int siguienteId() {
			
		return  modulos.stream().mapToInt(Modulo::getId).max().orElse(0)+1;
			
	}
}
