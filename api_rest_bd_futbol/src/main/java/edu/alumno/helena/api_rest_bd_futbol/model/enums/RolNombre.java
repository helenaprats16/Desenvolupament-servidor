package edu.alumno.helena.api_rest_bd_futbol.model.enums;


public enum RolNombre{
    ROLE_ADMIN("Administrador con acceso completo"), 
    ROLE_USER("Usuario estándar"), 
    ROLE_ENTRENADOR("Moderador con permisos intermedios"), 
    ROLE_JUGADOR("Jugador de un equipo de futbol"), 
    ROLE_ARBITRO("Arbitro de un partido");
    private final String descripcion;

    RolNombre(String descripcion){
        this.descripcion=descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}