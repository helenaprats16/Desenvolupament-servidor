package org.alumne.helena.primer_app_boot.model.ram;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioEdit implements Serializable,Comparable<UsuarioEdit>{

	@Size(min=3,message="El nickname debe tener un tamaño minimo de 3 carácteres")
	private String nickname;

	@Size(min=5,message="El password debe tener almenos 5 carácteres")
    private String passw;

	
    
    
    
    
	@Override
	public int compareTo(UsuarioEdit arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	public UsuarioEdit() {
		super();
	}
	public UsuarioEdit(String nickname,
			@NotNull(message = "El nickname debe tener un tamaño minimo de 3 carácteres") String passw) {
		super();
		this.nickname = nickname;
		this.passw = passw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
}
