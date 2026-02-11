package org.simarro.alumno.helena.instituto.srv;

import org.simarro.alumno.helena.instituto.model.dto.AlumnoEdit;

public interface AlumnoService {
    public AlumnoEdit create(AlumnoEdit alumnoEdit);
    public AlumnoEdit read(String dni);
    public AlumnoEdit update(String dni, AlumnoEdit alumnoEdit);
    public void delete(String dni);
}
