package org.alumne.helena.primer_app_spring_boot.srv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.alumne.helena.primer_app_boot.model.Alumno;
import org.springframework.stereotype.Service;

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
	
	public List<Alumno> listaAlumnos(){
		return alumnos;
	}

	//crear metodo para añadir un alumno al servicio
	public void addAlumno(Alumno alumno) throws Exception{
		
		for (Alumno a : alumnos) {
			if (a.getDni().equalsIgnoreCase(alumno.getDni())) {
				throw new Exception("El alumno con DNI "+a.getDni()+" ya existe.");
			}
		} 
		//si no hi ha duplicat es guarda
		alumnos.add(alumno);

	}
	
	public void borrarAlumno(Alumno alumno) {
		alumnos.remove(alumnos.indexOf(alumno));
	}
	
	
	//encontrar al alumno con el dni pasado por parametro
	public Alumno encontrarAlumnoPorDni(String dni) {
		Optional<Alumno> optalumno = alumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst(); 
		
		if(optalumno.isPresent()) {
			return optalumno.get();

		}else {
			return null;

		}
		
				
	}
	
	
	//modificar los datos de un alumno
	public void modificaAlumno(Alumno alumno) {
		borrarAlumno(alumno);//elimar el alumno existente
		alumnos.add(alumno);//añadir el nuevo alumno

	}
	
	
}
