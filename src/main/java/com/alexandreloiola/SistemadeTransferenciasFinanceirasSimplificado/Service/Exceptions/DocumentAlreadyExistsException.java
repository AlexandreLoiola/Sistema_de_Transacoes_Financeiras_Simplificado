package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class DocumentAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DocumentAlreadyExistsException(String msg) {
        super(msg);
    }
    public DocumentAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
