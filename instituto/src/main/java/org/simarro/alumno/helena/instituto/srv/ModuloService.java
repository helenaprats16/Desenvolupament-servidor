package org.simarro.alumno.helena.instituto.srv;

import org.simarro.alumno.helena.instituto.model.dto.ModuloEdit;

public interface ModuloService {
    public ModuloEdit create(ModuloEdit moduloEdit);
    public ModuloEdit read(Long id);
    public ModuloEdit update(Long id , ModuloEdit moduloEdit);
    public void delete(Long id);
}
