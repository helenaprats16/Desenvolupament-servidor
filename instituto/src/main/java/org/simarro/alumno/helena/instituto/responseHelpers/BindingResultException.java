package org.simarro.alumno.helena.instituto.responseHelpers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BindingResultException extends RuntimeException {
    
    private final BindingResult bindingResult;
    
    public BindingResultException(BindingResult bindingResult) {
        super("Errores de validación en los datos de entrada");
        this.bindingResult = bindingResult;
    }
    
    public BindingResult getBindingResult() {
        return bindingResult;
    }
}