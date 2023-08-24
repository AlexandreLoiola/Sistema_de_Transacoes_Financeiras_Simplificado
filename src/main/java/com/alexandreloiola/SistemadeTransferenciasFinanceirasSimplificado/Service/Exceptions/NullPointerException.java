package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class NullPointerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NullPointerException(String msg) { super(msg); }

    public NullPointerException(String msg, Throwable cause) {super(msg, cause);}
}
