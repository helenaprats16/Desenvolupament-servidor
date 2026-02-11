package edu.alumno.helena.api_rest_bd_futbol.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import edu.alumno.helena.api_rest_bd_futbol.model.dto.FiltroBusqueda;
import edu.alumno.helena.api_rest_bd_futbol.model.enums.TipoOperacionBusqueda;

public class FiltroBusquedaHelper {

    private final static CharSequence separador = ":";

    /**
     * Convierte un array de cadenas en una lista de FiltroBusqueda.
     * Detecta el operador (>, <, =) automáticamente y maneja valores que contienen el operador.
     *
     * @param filtros Array de cadenas en formato "atributo>valor", "atributo<valor", "atributo=valor".
     * @return Lista de FiltroBusqueda.
     */
    public static List<FiltroBusqueda> createFiltroBusqueda(String[] filtros) {

        if (filtros == null || filtros.length == 0) {
            return Collections.emptyList();
        }

        return Arrays.stream(filtros)
                    .map(FiltroBusquedaHelper::parsearFiltro)
                    .collect(Collectors.toList());

    }

    private static FiltroBusqueda parsearFiltro(String filtro){
        
        if (filtro == null  || !filtro.contains(separador)) {
            throw new IllegalArgumentException("El filtro proporcionado no tiene el formato esperado (ATRIBUTO "+separador.toString() + "OPERACION" +separador.toString()+ "VALOR).");
        }

        String[] partes = filtro.split(separador.toString(), 3);
        if (partes.length < 3) {
            throw new IllegalArgumentException("El filtro proporcionado no tiene el formato esperado (ATRIBUTO "+separador.toString() + "OPERACION" +separador.toString()+ "VALOR).");
        }

        String atributo = partes[0].trim();
        String operacionTexto = partes[1].trim();
        //Concatenar las partes restantes para el valor si hay mas de 3 partes
        String valor = Arrays.stream(partes, 2,partes.length)
                    .reduce((a,b) -> a + separador.toString() + b)
                    .orElse("").trim();
        TipoOperacionBusqueda operacion;
        try {
            operacion = TipoOperacionBusqueda.valueOf(operacionTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Operación no válida: "+operacionTexto);
        }
        
        
        
        return new FiltroBusqueda(atributo, operacion, valor);
    }

   

}
