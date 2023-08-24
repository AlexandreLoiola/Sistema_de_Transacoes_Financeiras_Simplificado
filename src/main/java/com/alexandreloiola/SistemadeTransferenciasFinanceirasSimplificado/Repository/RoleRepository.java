package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
    Optional<RoleModel> findByDescription(String description);
}
