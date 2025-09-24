package org.alumne.helena.primer_app_spring_boot.srv;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean usuarioValido(String usuario,String password) {
		if (usuario.contentEquals("helena")&& password.contentEquals("1234")) {
			return true;
		}else {
			return false;
		}
	}
	
}
