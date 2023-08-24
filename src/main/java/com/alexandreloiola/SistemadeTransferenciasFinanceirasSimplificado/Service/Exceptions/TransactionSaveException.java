package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Service.Exceptions;

public class TransactionSaveException extends RuntimeException {

    public TransactionSaveException(String message) {
        super(message);
    }
    public TransactionSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
