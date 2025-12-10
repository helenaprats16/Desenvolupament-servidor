package edu.alumno.helena.api_rest_bd_futbol.model.db;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "jornadas")
public class JornadaDb implements Serializable{

    @GeneratedValue(strategy = GenerationType.IDENTITY)//que se genera automaticamente
    private @Id Long num; //@Id es un id
    private String fecha;





}