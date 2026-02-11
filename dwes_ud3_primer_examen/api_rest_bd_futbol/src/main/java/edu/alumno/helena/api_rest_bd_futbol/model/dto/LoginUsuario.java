package edu.alumno.helena.api_rest_bd_futbol.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUsuario{
    @Size(min=5, message="El nickname debe de tener un tamaño mínimo de 5 caracters")
    private String nickname;
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message= "El password debe tener al menos 8 caracteres, una mayuscula, una minúscula, un número y un caracter especial"
    )
    private String password;
}