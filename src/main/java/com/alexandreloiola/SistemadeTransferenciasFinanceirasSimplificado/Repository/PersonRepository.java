package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.PersonModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, UUID> {
    Optional<PersonModel> findByDocument(String document);

}
