package org.alumne.helena.primer_app_boot.model;

public class Pagina {

	String titulo;
	String paginaActiva;	
	
	
	public Pagina(String titulo, String paginaActiva) {
		super();
		this.titulo = titulo;
		this.paginaActiva = paginaActiva;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPaginaActiva() {
		return paginaActiva;
	}
	public void setPaginaActiva(String paginaActiva) {
		this.paginaActiva = paginaActiva;
	}

	
	
	public String getStrBootstrapActiva(String pagina) {
		if (paginaActiva.equals(pagina)) {
			return "active";
		} else {
			return "";
		}
	}



}
