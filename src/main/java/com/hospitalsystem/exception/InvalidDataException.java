package com.hospitalsystem.exception;

/**
 * Exceção customizada para dados inválidos
 */
public class InvalidDataException extends RuntimeException {
    
    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
