package edu.alumne.helena.dwes_primer_rest.exception;

public class AlumnoNotFoundExcepcion extends RuntimeException {

    private final String errorCode;
    private static final long serialVersionUID = 1L;
    public AlumnoNotFoundExcepcion(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
