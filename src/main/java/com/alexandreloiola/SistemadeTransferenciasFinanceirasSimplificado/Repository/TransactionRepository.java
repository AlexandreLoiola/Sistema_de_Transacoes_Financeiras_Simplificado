package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
}
