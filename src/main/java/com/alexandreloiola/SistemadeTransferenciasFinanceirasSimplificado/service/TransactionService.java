package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.BusinessRuleException;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.TransactionModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.UserModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository.TransactionRepository;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.TransactionDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.TransactionForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.TransactionSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public TransactionDto CreateTransaction(TransactionForm transactionForm) {
        validateSelfTransaction(transactionForm.getSenderId(), transactionForm.getReceiverId());

        UserModel sender = userService.getUserModelById(transactionForm.getSenderId());
        UserModel receiver = userService.getUserModelById(transactionForm.getReceiverId());

        validateSenderBalance(sender, transactionForm.getAmount());
        validateSenderRole(sender);
        authorizeTransaction(sender, transactionForm.getAmount());

        transferAmount(sender, receiver, transactionForm.getAmount());
        TransactionDto newTransaction = insertTransaction(sender, receiver, transactionForm.getAmount());
        return newTransaction;
    }

    @Transactional
    public TransactionDto insertTransaction(UserModel sender, UserModel receiver, BigDecimal amount) {
        try {
            TransactionModel newTransaction = new TransactionModel();
            newTransaction.setAmount(amount);
            newTransaction.setSender(sender);
            newTransaction.setReceiver(receiver);
            newTransaction.setTimestamp(LocalDateTime.now());
            newTransaction = transactionRepository.save(newTransaction);
            return convertModelToDto(newTransaction);
        } catch (DataAccessException e) {
            throw new TransactionSaveException("Erro ao salvar a transação");
        }
    }

    @Transactional
    private void transferAmount(UserModel sender, UserModel receiver, BigDecimal amount) {
        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
    }

    private void validateSenderBalance(UserModel sender, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new BusinessRuleException("Saldo insuficiente");
        }
    }

    private void validateSelfTransaction(UUID senderId, UUID receiverId) {
        if (senderId.equals(receiverId)) {
            throw new BusinessRuleException("Não é possível realizar transações para a própria conta");
        }
    }

    private void validateSenderRole(UserModel sender) {
        if (sender.getRole().getDescription().equals("Merchant")) {
            throw new BusinessRuleException("Ao lojista não é permitido este tipo de transação");
        }
    }

    private boolean authorizeTransaction(UserModel sender, BigDecimal value) {
        ResponseEntity<Map> response = restTemplate
                .getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().get("message").equals("Autorizado")) {
            return true;
        } else {
            throw new BusinessRuleException("Transação não autorizada");
        }
    }

    private TransactionDto convertModelToDto(TransactionModel transactionModel) {
        if (transactionModel == null) {
            throw new IllegalArgumentException("O objeto transação não pode ser nulo");
        }
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionModel.getAmount());
        transactionDto.setSender(transactionModel.getSender().getId());
        transactionDto.setReceiver(transactionModel.getReceiver().getId());
        return transactionDto;
    }
}