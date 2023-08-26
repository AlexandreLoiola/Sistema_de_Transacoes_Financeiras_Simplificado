package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.PersonModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository.PersonRepository;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.PersonDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.PersonForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.DocumentAlreadyExistsException;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonDto getPersonDtoByDocument(String document) {
        Optional<PersonModel> personModel = personRepository.findByDocument(document);
        if (!personModel.isPresent()){
            throw new ObjectNotFoundException("Pessoa não encontrada!");
        }
        PersonDto personDto = convertModelToDto(personModel.get());
        return personDto;
    }

    public PersonModel getPersonModelByDocument(String document) {
        Optional<PersonModel> personModel = personRepository.findByDocument(document);
        if (!personModel.isPresent()){
            throw new ObjectNotFoundException("Pessoa não encontrada!");
        }
        return personModel.get();
    }

    @Transactional
    public PersonDto insertPerson(PersonForm personForm) {
        validatePersonDocument(personForm.getDocument());
        try {
            PersonModel newPerson = new PersonModel();
            newPerson.setName(personForm.getName());
            newPerson.setDocument(personForm.getDocument());
            newPerson.setIsActive(true);
            newPerson = personRepository.save(newPerson);
            return convertModelToDto(newPerson);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Ocorreu um erro ao tentar alterar o papel de usuário. Por favor, tente novamente mais tarde.");
        }
    }

    private void validatePersonDocument(String document) {
        Optional<PersonModel> personModel
                = personRepository.findByDocument(document);
        if (personModel.isPresent()) {
            throw new DocumentAlreadyExistsException("Este documento já está cadastrado");
        }
    }

    public PersonDto convertModelToDto(PersonModel personModel) {
        if (personModel == null) {
            throw new IllegalArgumentException("O objeto pessoa não pode ser nulo");
        }
        PersonDto personDto = new PersonDto();
        personDto.setName(personModel.getName());
        personDto.setDocument(personModel.getDocument());
        return personDto;
    }
}