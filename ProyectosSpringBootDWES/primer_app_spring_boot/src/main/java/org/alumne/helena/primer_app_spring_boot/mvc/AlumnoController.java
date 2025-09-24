package org.alumne.helena.primer_app_spring_boot.mvc;

import org.alumne.helena.primer_app_boot.model.Alumno;
import org.alumne.helena.primer_app_boot.model.Pagina;
import org.alumne.helena.primer_app_spring_boot.srv.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("nombre")
public class AlumnoController {

	@Autowired
	AlumnoService alumnoService;
	Pagina pagina = new Pagina("Llistat alumnes","list-alumno"); 
	Alumno alumno;
	

	@RequestMapping(value = "/list-alumno",method = RequestMethod.GET)
	public String mostrarLogin(ModelMap map ){
		map.put("alumno", alumnoService.listaAlumnos());
		map.put("pagina", pagina);
		return "list-alumno";
		
		
	}
	@RequestMapping(value = "/add-alumno",method = RequestMethod.GET)
	public String mostrarAlumno(ModelMap map ){
		map.addAttribute("alumno", new Alumno("","Nuevo Alumno",18,"DAW",2));
		return "add-alumno";
	
	}		
	@RequestMapping(value="/add-alumno", method = RequestMethod.POST)
	public String addAlumno(ModelMap model,@Valid Alumno alumno,  BindingResult validacion) {//BindingResult per  comprobar si la validacio te errors o no
	    String errores = "";
	    model.addAttribute("pagina", pagina);
	    if (validacion.hasErrors()) {
			//hay errores y debemos volver al formulario de alta
	    	return "add-alumno";
		}
	    try {
	        alumnoService.addAlumno(alumno);
	        // Para evitar pasar parametros innecesarios
	        model.clear();
	        return "redirect:list-alumno";
	    } catch (Exception e) {
	        errores = e.toString();
	        model.addAttribute("errores", errores);
	        return "add-alumno";
	    }
	}
	
	@RequestMapping(value = "del-alumno",method = RequestMethod.GET)
	public String borrarAlumno(@RequestParam String dni, ModelMap model) {
		alumnoService.borrarAlumno(new Alumno(dni));
        return "redirect:list-alumno";
		
	}
	
	@RequestMapping(value = "/update-alumno",method = RequestMethod.GET)
	public String updateAlumnos(@RequestParam String dni, ModelMap model) {
		Alumno alumnoExistente =alumnoService.encontrarAlumnoPorDni(dni);
		
		model.addAttribute("alumno",alumnoExistente);
        return "update-alumno";
		
	}
	
	@RequestMapping(value = "/update-alumno", method = RequestMethod.POST)
	public String procesaUpdateAlumnos(@Valid Alumno alumno, BindingResult validacion, ModelMap model) {
	    // validamos els datos
	    if (validacion.hasErrors()) {
	        model.addAttribute("errores", "Por favor corrige los errores");
	        return "update-alumno";
	    }

	    try {
	        alumnoService.modificaAlumno(alumno);
	        model.clear();
	        // redirigim al llistat
	        return "redirect:list-alumno";
	    } catch (Exception e) {
	        model.addAttribute("errores", e.getMessage());
	        return "update-alumno";
	    }
	}

	
	
	
	
	
	
	
	

	
}

