package org.simarro.alumno.helena.instituto.helper;

import org.springframework.stereotype.Component;

@Component //si los métodos no son static debemos crear un bean
public class DataIntegrityViolationAnalyzer {
    
    public static String analyzeErrorCode(String detailedMessage) {
        if (detailedMessage != null && detailedMessage.contains("foreign key")) {
            return "FOREIGN_KEY_VIOLATION";
        } else if (detailedMessage != null && detailedMessage.contains("unique constraint")) {
            return "UNIQUE_CONSTRAINT_VIOLATION";
        }
        return "DATA_INTEGRITY_VIOLATION";
    }
    
    public static String analyzeUserMessage(String detailedMessage) {
        if (detailedMessage != null && detailedMessage.contains("foreign key")) {
            return "The provided foreign key value does not exist in the related table.";
        } else if (detailedMessage != null && detailedMessage.contains("unique constraint")) {
            return "A record with the same unique value already exists.";
        }
        return "There was a data integrity violation. Please check your input.";
    }
}
