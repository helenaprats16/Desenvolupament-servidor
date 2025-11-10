package edu.alumno.helena.dwes_futbol_rest.model.dto;

import java.io.Serializable;

import org.mapstruct.Mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Mapper(componentModel = "spring")
public class JornadaList implements Serializable{
    private static final long serialVersionUID=1L;
    private Long num;
    private String fecha;
}