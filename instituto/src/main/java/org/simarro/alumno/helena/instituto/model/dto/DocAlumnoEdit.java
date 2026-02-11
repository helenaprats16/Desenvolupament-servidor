package org.simarro.alumno.helena.instituto.model.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocAlumnoEdit {

    @Schema(example = "1", description = "Identificador único del documento")
    private Long id;

    @Pattern(regexp = "[0-9]{8}[A-Z]{1}", message = "El dni debe tener 8 numeros y una letra")
    @Schema(example = "12345678A", description = "DNI del alumno, formado por 8 números y una letra")
    private String dni;

    @Size(min = 5, message = "El nombre del fichero debe tener un tamaño mínimo de 5 caracteres")
    @Size(max = 255, message = "El nombre del fichero debe tener un tamaño máximo de 255 caracteres")
    @Schema(example = "DocumentoImportante.pdf", description = "Nombre del fichero entre 5 y 255 caracteres")
    private String nombreFichero;

    @Schema(example = "Comentarios adicionales sobre el documento", description = "Descripción adicional del documento")
    private String comentario;

    // Campo para el archivo (no se persiste directamente en la base de datos. Debe pasarse a base64)
    @Schema(example = "Selecciona el archivo...", description = "Archivo del documento en formato multipart")
    private MultipartFile multipart;

    @Size(max = 255, message = "El nombre del creador debe tener un tamaño máximo de 255 caracteres")
    @Schema(example = "Simarro", description = "Nombre del usuario que creó el documento")
    private String creadoPor;
}
