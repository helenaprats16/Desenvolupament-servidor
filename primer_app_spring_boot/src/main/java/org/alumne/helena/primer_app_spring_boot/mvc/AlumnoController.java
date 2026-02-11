package org.alumne.helena.primer_app_spring_boot.mvc;


import org.alumne.helena.primer_app_boot.model.ram.Alumno;
import org.alumne.helena.primer_app_boot.model.ram.Pagina;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.srv.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("nombre")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	private Pagina pagina = new Pagina("Llistat alumnes","list-alumno"); 
	
	
	/*LLISTAR*/

	@RequestMapping(value = "/list-alumno",method = RequestMethod.GET)
	public String mostrarLogin(ModelMap map ){
		map.put("alumnosList", alumnoService.listaAlumnos());
		map.put("pagina", pagina);
		return "list-alumno";
		
		
	}
	
	/*AFEGIR*/
	
	
	
	@RequestMapping(value = "/add-alumno",method = RequestMethod.GET)
	public String mostrarAlumno(ModelMap map ){
		map.addAttribute("alumnoEdit", new AlumnoEdit());
		return "add-alumno";
	
	}		
	@RequestMapping(value="/add-alumno", method = RequestMethod.POST)
	public String addAlumno(ModelMap model,@Valid @ModelAttribute("alumnoEdit") AlumnoEdit alumno,  BindingResult validacion) {//BindingResult per  comprobar si la validacio te errors o no
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
	        model.addAttribute("alumno", new Alumno());
	        
	    }
	    
	    model.addAttribute("errores",errores);
	    return "add-alumno";
	    
	}
	
	
	
	/*BORRAR*/
	
	@RequestMapping(value = "/del-alumno",method = RequestMethod.GET)
	public String borrarAlumno(ModelMap model, @RequestParam String dni) {
		
		model.addAttribute("alumno",alumnoService.encontrarAlumnoInfoPorDni(dni));
        return "delete-alumno";
		
	}
	
	@RequestMapping(value = "/del-alumnoborrar",method = RequestMethod.GET)
	public String borrarAlumnoEncontrado(ModelMap model, @RequestParam String dni) {
		
		alumnoService.borrarAlumno(dni);
        return "redirect:list-alumno";
		
	}
	
	
	/*MODIFICAR*/
	

	
	@RequestMapping(value = "/update-alumno",method = RequestMethod.GET)
	public String updateAlumnos(ModelMap model,@RequestParam String dni) {
		
		model.addAttribute("alumnoEdit",alumnoService.encontrarAlumnoEditPorDni(dni));
        return "update-alumno";
		
	}
	
	
	
	
	
	@RequestMapping(value = "/update-alumno", method = RequestMethod.POST)
	public String procesaUpdateAlumnos( ModelMap model, @Valid @ModelAttribute("alumnoEdit") AlumnoEdit alumno, BindingResult validacion) {
	    
		
		// validamos els datos
	    if (validacion.hasErrors()) {
	        model.addAttribute("errores", "Por favor corrige los errores");
	        return "update-alumno";
	    }

	    // No guardamos todavía — solo mostramos confirmación

	    model.addAttribute("alumnoEdit",alumno);
	    return "updateconfirm-alumno";
	    
	  }

	
	
	@RequestMapping(value = "/updateconfirm-alumno", method = RequestMethod.POST)
	public String confirmarUpdateAlumnos(ModelMap model, 
	                                     @ModelAttribute("alumnoEdit") AlumnoEdit alumno) {
	    try {
	        alumnoService.modificaAlumnoEdit(alumno);
	        model.clear();
	        return "redirect:list-alumno";
	    } catch (Exception e) {
	        model.addAttribute("errores", e.getMessage());
	        return "updateconfirm-alumno";
	    }
	}
	
	
	
	
	
	
	
	

	
}

