package org.simarro.alumno.helena.instituto.srv;

import org.simarro.alumno.helena.instituto.model.dto.CursoEdit;

public interface CursoService {
    public CursoEdit create(CursoEdit cursoEdit);
    public CursoEdit read(Long id);
    public CursoEdit update(Long id , CursoEdit cursoEdit);
    public void delete(Long id);
}
