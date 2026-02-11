package edu.alumno.helena.api_rest_bd_futbol.model.dto;

import java.io.Serializable;

import org.mapstruct.Mapper;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaList implements Serializable{
    private static final long serialVersionUID=1L;
    private Long id;
    private String nombre;
    private String pais;
    private Integer fundacion;
    private Boolean activa;
    @Column(name="sitio_web")
    private String sitioWeb;

}