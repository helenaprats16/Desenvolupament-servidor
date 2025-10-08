package org.alumne.helena.primer_app_spring_boot;

import org.alumne.helena.primer_app_boot.model.ram.Pagina;
import org.alumne.helena.primer_app_spring_boot.srv.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("nombre")
public class LoginController {

	@Autowired
	LoginService loginServicio;
	Pagina pagina = new Pagina("Login","login"); 
	
	@RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
	public String mostrarLogin(ModelMap model) {
		model.put("pagina", pagina);
		return "login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String procesaLogin(@RequestParam String nombre, @RequestParam String password, ModelMap model) {
		if (!loginServicio.usuarioValido(nombre, password)) {
			//usuario invalido. volver a intentar logearse
			model.put("errores", "Usuario '"+nombre+"' o contraseña incorrecta");
			return "login";
		} else {//usuario valido
			model.put("nombre", nombre);			
			return "redirect:list-alumno";
		}
	}
	
}
