package org.simarro.alumno.helena.instituto.responseHelpers;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class CustomErrorResponse {
    private String error;
    private String mensaje;
    private String codigoError;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    
    private String path;
    
    public CustomErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public CustomErrorResponse(String error, String mensaje, String codigoError) {
        this();
        this.error = error;
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }
    
    // Getters y Setters
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getCodigoError() {
        return codigoError;
    }
    
    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
}