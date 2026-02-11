package org.alumne.helena.primer_app_spring_boot;

import org.alumne.helena.primer_app_boot.model.ram.Pagina;
import org.alumne.helena.primer_app_boot.model.ram.UsuarioEdit;
import org.alumne.helena.primer_app_spring_boot.srv.LoginService;
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
public class LoginController {

	@Autowired
	LoginService loginServicio;
	Pagina pagina = new Pagina("Login","login"); 
	
	@RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
	public String mostrarLogin(ModelMap model) {
		UsuarioEdit userEdit =new UsuarioEdit();
		model.put("usuarioEdit", userEdit);
		model.put("pagina", pagina);
		return "login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String procesaLogin(ModelMap model, @Valid @ModelAttribute("usuarioEdit") UsuarioEdit user, BindingResult validacio) {
		
		if (validacio.hasErrors()) {
	        return "login";
	    }
		
		
		if (loginServicio.usuarioValido(user.getNickname(), user.getPassw())) {
			
			model.put("nombre", user.getNickname());			
			return "redirect:list-alumno";
			
		} else {
			//usuario invalido. volver a intentar logearse
			model.put("errores", "Usuario '"+user.getNickname()+"' o contraseña incorrecta");
			return "login";
		}
		
		
		
	}
	
}
