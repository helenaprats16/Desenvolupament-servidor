package org.simarro.alumno.helena.instituto.responseHelpers;

import java.util.ArrayList;
import java.util.List;

public class BindingResultErrorsResponse {
    private String mensaje;
    private List<CampoError> errores;
    
    public BindingResultErrorsResponse() {
        this.errores = new ArrayList<>();
    }
    
    public BindingResultErrorsResponse(String mensaje) {
        this();
        this.mensaje = mensaje;
    }
    
    public void agregarError(String campo, String mensajeError) {
        this.errores.add(new CampoError(campo, mensajeError));
    }
    
    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public List<CampoError> getErrores() {
        return errores;
    }
    
    public void setErrores(List<CampoError> errores) {
        this.errores = errores;
    }
    
    // Clase interna para errores de campo
    public static class CampoError {
        private String campo;
        private String mensaje;
        
        public CampoError(String campo, String mensaje) {
            this.campo = campo;
            this.mensaje = mensaje;
        }
        
        // Getters
        public String getCampo() {
            return campo;
        }
        
        public String getMensaje() {
            return mensaje;
        }
    }
}