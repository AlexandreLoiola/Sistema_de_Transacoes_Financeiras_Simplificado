package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
    public EmailAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
