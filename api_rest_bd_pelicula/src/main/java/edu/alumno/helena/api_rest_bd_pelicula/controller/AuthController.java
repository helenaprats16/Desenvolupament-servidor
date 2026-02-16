package edu.alumno.helena.api_rest_bd_pelicula.controller;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.alumno.helena.api_rest_bd_pelicula.model.dto.AuthRequest;
import edu.alumno.helena.api_rest_bd_pelicula.model.dto.AuthResponse;
import edu.alumno.helena.api_rest_bd_pelicula.security.jwt.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
// Eliminat Lombok

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;



    /**
     * Endpoint per a registrar un nou usuari (exemple bàsic, adapta-ho al teu model real)
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dades incorrectes");
        }
        // Aquí hauries de fer la lògica de registre (comprovar duplicats, guardar a la BD, etc.)
        // ...
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuari registrat");
    }

    /**
     * Endpoint per a login d'usuari
     */
    @Operation(summary = "Autenticació d'usuari", description = "Valida les credencials i retorna un JWT si són correctes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login correcte. Retorna el token JWT.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credencials incorrectes.", content = @Content(mediaType = "application/json", schema = @Schema(example = "Unauthorized")))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dades incorrectes");
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtService.generateToken(auth.getName());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
