package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Rest.Form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionForm {

    @NotNull
    private BigDecimal amount;
    @NotNull(message = "O id do remetente não pode ser nulo")
    private UUID senderId;
    @NotNull(message = "O id do destinatário não pode ser nulo")
    private UUID receiverId;

}
