package org.alumne.helena.primer_app_spring_boot.mvc;

import java.util.List;

import org.alumne.helena.primer_app_boot.model.ram.DocAlumno;
import org.alumne.helena.primer_app_boot.model.ram.Pagina;
import org.alumne.helena.primer_app_spring_boot.srv.AlumnoService;
import org.alumne.helena.primer_app_spring_boot.srv.DocAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
public class DocAlumnoController {

	@Autowired
	private DocAlumnoService das;
	@Autowired
	private AlumnoService alumnoService;
	
	private Pagina pagina;


	
	@RequestMapping(value = "/docs-alumno",method = RequestMethod.GET)
	public String mostrarDoc(ModelMap map,@RequestParam String dni ){
		
		DocAlumno docAlumno = new DocAlumno();
		
		
		map.put("alumno", alumnoService.encontrarAlumnoInfoPorDni(dni));
		map.put("opcionesTipoDoc", das.listatipoDoc());//lista de Strings
		map.put("docAlumnos", das.llistaDocsAlumnes(dni));//lista de DocAlumnos
		map.put("docAlumno", docAlumno);
		return "docs-alumno";
		
		
	}
	
	
	@ModelAttribute("opcionesTipoDoc")
	public List<String> getListaTipoDoc(){
		return das.listatipoDoc();
	}
	
	@PostMapping("/add-docAlumno")
	public String addDocAlumno(ModelMap map,@Valid DocAlumno docAlumno, BindingResult validacion, @RequestParam String dni) {
		
		
		map.put("alumno", alumnoService.encontrarAlumnoInfoPorDni(dni));
		map.put("opcionesTipoDoc", das.listatipoDoc());//lista de Strings
		map.put("docAlumnos", das.llistaDocsAlumnes(dni));//lista de DocAlumnos
		map.put("docAlumno", docAlumno);
		
		
		//validacions en @Valid
		if (validacion.hasErrors()) {
	        return "docs-alumno";
		}

		
	    try {
	    	
	    	das.addDocAlumno(docAlumno);
	        
	        return "redirect:/docs-alumno?dni=" + dni; // redirección limpia
	        
	        
	    } catch (Exception e) {
	        map.addAttribute("errores", e.getMessage());
	        return "docs-alumno";
	    }
		
	    
	   
	    
	}

	
	
	
	
	

	
}

