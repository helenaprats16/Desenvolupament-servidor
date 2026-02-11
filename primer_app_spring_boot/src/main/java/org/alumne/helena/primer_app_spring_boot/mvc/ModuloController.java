package org.alumne.helena.primer_app_spring_boot.mvc;

import org.alumne.helena.primer_app_boot.model.ram.Alumno;

import org.alumne.helena.primer_app_boot.model.ram.Pagina;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoEdit;
import org.alumne.helena.primer_app_spring_boot.model.dto.AlumnoInfo;
import org.alumne.helena.primer_app_spring_boot.model.dto.ModuloEdit;
import org.alumne.helena.primer_app_spring_boot.srv.AlumnoService;
import org.alumne.helena.primer_app_spring_boot.srv.ModuloService;
import org.alumne.helena.primer_app_spring_boot.srv.mapper.AlumnoMapper;
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
public class ModuloController {

	@Autowired
	private ModuloService moduloService;

	/* AFEGIR */

	@RequestMapping(value = "/list-modulos", method = RequestMethod.GET)
	public String mostrarModulos(ModelMap map) {
		map.addAttribute("moduloList", moduloService.listaModulos());
		map.addAttribute("moduloEdit", new ModuloEdit());
		return "list-modulos";

	}

	@RequestMapping(value = "/add-modulo", method = RequestMethod.POST)
	public String addModulo(ModelMap model, @Valid @ModelAttribute("moduloEdit") ModuloEdit modulo,
			BindingResult validacion) {// BindingResult per comprobar si la validacio te errors o no
		if (validacion.hasErrors()) {
			model.addAttribute("moduloList", moduloService.listaModulos());
			return "list-modulos";
		}

		try {
			moduloService.addModulo(modulo);
			model.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list-modulos";

	}

}
