package org.simarro.alumno.helena.instituto.srv.impl;

import org.simarro.alumno.helena.instituto.exception.EntityIlegalArgumentException;
import org.simarro.alumno.helena.instituto.exception.EntityNotFoundException;
import org.simarro.alumno.helena.instituto.model.db.ModuloEditDb;
import org.simarro.alumno.helena.instituto.model.dto.ModuloEdit;
import org.simarro.alumno.helena.instituto.repository.ModuloEditRepository;
import org.simarro.alumno.helena.instituto.srv.ModuloService;
import org.simarro.alumno.helena.instituto.srv.mapper.ModuloMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Esta notacion Lombok permite no tener que declarar el constructor
public class ModuloServiceImpl implements ModuloService {

    private final ModuloEditRepository moduloRepository;

    @Override
    public ModuloEdit create(ModuloEdit modulo) {
        if (modulo.getId() != null) { // Como el id lo crea la BD no debemos pasarle un valor inicial
        throw new EntityIlegalArgumentException("MODULE_ID_MISMATCH",
        "El ID debe ser nulo al crear un nuevo módulo.");
    }

        ModuloEditDb entity = ModuloMapper.INSTANCE.ModuloEditToModuloEditDb(modulo);
        return ModuloMapper.INSTANCE.ModuloEditDbToModuloEdit(moduloRepository.save(entity));
    }

    @Override
    public ModuloEdit read(Long id) {
        ModuloEditDb entity = moduloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("MODULE_NOT_FOUND",
                        "El módulo con ID "+ id + " no existe"));
        return ModuloMapper.INSTANCE.ModuloEditDbToModuloEdit(entity);
    }

    @Override
    public ModuloEdit update(Long id, ModuloEdit modulo) {
        if (!id.equals(modulo.getId())) {
            throw new EntityIlegalArgumentException("MODULE_ID_MISMATCH",
                "El ID proporcionado no coincide con el ID del módulo");   
        }

        ModuloEditDb existingEntity = moduloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MODULE_NOT_FOUND_FOR_UPDATE",
                        "No se puede actualizar. El módulo con ID "+ id+" no existe."));
            ModuloMapper.INSTANCE.updateModuloEditDbFromModuloEdit(modulo, existingEntity);           
            return ModuloMapper.INSTANCE.ModuloEditDbToModuloEdit(moduloRepository.save(existingEntity));
        }   

    @Override
    public void delete(Long id) {
        if (moduloRepository.existsById(id)) {
            moduloRepository.deleteById(id);
        } // Si no existe, no hacemos nada: el estado final "registro no existe" ya está
    
    }
}
