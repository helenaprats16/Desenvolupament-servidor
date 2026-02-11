package org.simarro.alumno.helena.instituto.filters.model;

import java.util.Arrays;

public class FiltroBusqueda {
    private String campo;
    private String operador;
    private String valor;
    
    // Constructores
    public FiltroBusqueda() {}
    
    public FiltroBusqueda(String campo, String operador, String valor) {
        this.campo = campo;
        this.operador = operador;
        this.valor = valor;
    }
    
    /**
     * Constructor que parsea un filtro en formato "campo:operador:valor"
     */
    public FiltroBusqueda(String filtroString) {
        if (filtroString != null && !filtroString.trim().isEmpty()) {
            String[] partes = filtroString.split(":");
            if (partes.length >= 3) {
                this.campo = partes[0];
                this.operador = partes[1];
                // El valor puede contener ":" así que unimos las partes restantes
                this.valor = String.join(":", Arrays.copyOfRange(partes, 2, partes.length));
            } else if (partes.length == 2) {
                this.campo = partes[0];
                this.operador = "eq"; // Operador por defecto
                this.valor = partes[1];
            }
        }
    }
    
    // Getters y Setters
    public String getCampo() {
        return campo;
    }
    
    public void setCampo(String campo) {
        this.campo = campo;
    }
    
    public String getOperador() {
        return operador;
    }
    
    public void setOperador(String operador) {
        this.operador = operador;
    }
    
    public String getValor() {
        return valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return String.format("%s:%s:%s", campo, operador, valor);
    }
    
    /**
     * Valida si el filtro es válido
     */
    public boolean esValido() {
        return campo != null && !campo.trim().isEmpty() && 
               operador != null && !operador.trim().isEmpty() &&
               valor != null;
    }
}