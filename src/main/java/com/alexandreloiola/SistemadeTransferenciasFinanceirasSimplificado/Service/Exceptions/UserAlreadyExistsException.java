package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String msg) { super(msg); }

    public UserAlreadyExistsException(String msg, Throwable cause) {super(msg, cause);}
}
