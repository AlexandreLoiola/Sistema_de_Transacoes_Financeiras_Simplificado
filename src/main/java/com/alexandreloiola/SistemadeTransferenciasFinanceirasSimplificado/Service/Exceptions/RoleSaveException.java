package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class RoleSaveException extends RuntimeException {
    public RoleSaveException(String message) {
        super(message);
    }
    public RoleSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
