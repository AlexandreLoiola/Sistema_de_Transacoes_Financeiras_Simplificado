package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class UserSaveException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserSaveException(String msg) { super(msg); }

    public UserSaveException(String msg, Throwable cause) {super(msg, cause);}
}
