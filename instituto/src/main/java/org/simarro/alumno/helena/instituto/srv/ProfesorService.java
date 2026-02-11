package org.simarro.alumno.helena.instituto.srv;

import org.simarro.alumno.helena.instituto.model.dto.ProfesorEdit;

public interface ProfesorService {
    public ProfesorEdit create(ProfesorEdit profesorEdit);
    public ProfesorEdit read(Long id);
    public ProfesorEdit update(Long id, ProfesorEdit profesorEdit);
    public void delete(Long id);
}
