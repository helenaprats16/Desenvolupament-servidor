package org.simarro.alumno.helena.instituto.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocAlumnoCountByNicknameResponse {
    @Size(max = 255, message = "El nombre del creador debe tener un tamaño máximo de 255 caracteres")
    @Schema(example = "Simarret", description = "Nombre del usuario que creó el documento")
    private String nickname;
    
    @Schema(example = "10", description = "Número de documentos creados por el nickname")
    private Long documentCount;
}
