package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Controller;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.TransactionDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.TransactionService;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.TransactionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> insertTransaction(
            @Valid @RequestBody TransactionForm transactionForm
    ) throws Exception {
        TransactionDto transactionDto = transactionService.CreateTransaction(transactionForm);
        return ResponseEntity.ok().body(transactionDto);
    }
}
