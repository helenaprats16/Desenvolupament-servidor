package org.simarro.alumno.helena.instituto.srv;

import org.simarro.alumno.helena.instituto.model.dto.MatriculaEdit;

public interface MatriculaService {
    public MatriculaEdit create(MatriculaEdit matriculaEdit);
    public MatriculaEdit read(Long id);
    public MatriculaEdit update(Long id , MatriculaEdit matriuclaEdit);
    public void delete(Long id);
}
