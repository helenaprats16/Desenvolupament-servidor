package org.alumne.helena.primer_app_spring_boot.srv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.alumne.helena.primer_app_boot.model.ram.Alumno;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoInfo;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoList;
import org.alumne.helena.primer_app_spring_boot.srv.mapper.AlumnoMapper;
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

@Service
public class AlumnoService {

	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	//1º Para evitar tener la lista de alumnos en blanco al arrancar la app debemos crear un bloque estático para
	//inicializar el ArrayList al principio con 3 valores: "Jose","Pedro" y "Juan"
	
	static {
		alumnos.add(new Alumno("11111111A", "Jose", 21, "DAM", 1));
		alumnos.add(new Alumno("22222222A", "Pepe", 32, "DAW", 2));
		alumnos.add(new Alumno("33333333A", "Juan", 23, "ASIR", 1));

	}
	
	//2º Crear un método listAlumnos() que devuelva la lista de alumnos
	
	public List<AlumnoList> listaAlumnos(){
		return AlumnoMapper.INSTANCE.alumnosToAlumnosList(alumnos);
	}

	//crear metodo para añadir un alumno al servicio
	public void addAlumno(AlumnoEdit alumnoEdit) throws Exception{
		
		
			if (alumnos.contains(new Alumno(alumnoEdit.getDni()))) {
				throw new Exception("El alumno con DNI "+alumnoEdit.getDni()+" ya existe.");
			}
		
		//si no hi ha duplicat es guarda
		alumnos.add(AlumnoMapper.INSTANCE.alumnoEditToAlumno(alumnoEdit));

	}
	
	public void borrarAlumno(String dni) {
		Alumno alumno = encontrarAlumnoPorDni(dni);
		alumnos.remove(alumno);
	}
	
	
	//encontrar al alumno con el dni pasado por parametro
	private Alumno encontrarAlumnoPorDni(String dni) {
		Optional<Alumno> optalumno = alumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst(); 
		
		if(optalumno.isPresent()) {
			return optalumno.get();

		}else {
			return null;

		}
		
				
	}
	
	
	//modificar los datos de un alumno
	public void modificaAlumnoEdit(@Valid AlumnoEdit alumno) {
	
		borrarAlumno(alumno.getDni());//elimar el alumno existente
		alumnos.add(AlumnoMapper.INSTANCE.alumnoEditToAlumno(alumno));

	}
	
	public AlumnoEdit encontrarAlumnoEditPorDni(String dni) {
		Alumno alumno = encontrarAlumnoPorDni(dni);
		return AlumnoMapper.INSTANCE.alumnoToAlumnoEdit(alumno);
		

	}
	
	public AlumnoInfo encontrarAlumnoInfoPorDni(String dni) {
		Alumno alumno = encontrarAlumnoPorDni(dni);
		return AlumnoMapper.INSTANCE.alumnoToAlumnoInfo(alumno);

	}
	
	
}
