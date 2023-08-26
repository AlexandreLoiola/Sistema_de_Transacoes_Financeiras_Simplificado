package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.PersonModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.RoleModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.UserModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository.UserRepository;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.PersonDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.RoleDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.UserDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.PersonForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.UserForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    public List<UserDto> getAllUsers() {
        List<UserModel> userDtoList = userRepository.findAll();
        return convertListToDto(userDtoList);
    }

    public UserDto getUserDtoByEmail(String email) {
        Optional<UserModel> userModel = userRepository.findByEmail(email);
        if (!userModel.isPresent()) {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }
        return convertModelToDto(userModel.get());
    }

    public UserModel getUserModelById(UUID id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (!userModel.isPresent()) {
            throw new ObjectNotFoundException("Pessoa não encontrada!");
        }
        return userModel.get();
    }

    public UserModel getUserModelByEmail(String email) throws Exception {
        Optional<UserModel> userModel = userRepository.findByEmail(email);
        if (!userModel.isPresent()) {
            throw new ObjectNotFoundException("Pessoa não encontrada!");
        }
        return userModel.get();
    }

    @Transactional
    public UserDto insertUser(UserForm userForm) {
        validateUserEmail(userForm.getEmail());
        try {
            RoleModel roleModel = roleService.getRoleModelByDescription(userForm.getRole());
            PersonForm newPerson = new PersonForm();
            newPerson.setName(userForm.getName());
            newPerson.setDocument(userForm.getDocument());
            personService.insertPerson(newPerson);
            PersonModel personModel = personService.getPersonModelByDocument(userForm.getDocument());

            UserModel newUser = new UserModel();
            newUser.setEmail(userForm.getEmail());
            newUser.setPassword(userForm.getPassword());
            newUser.setIsActive(true);
            newUser.setBalance(new BigDecimal(100));
            newUser.setPerson(personModel);
            newUser.setRole(roleModel);
            newUser = userRepository.save(newUser);

            return convertModelToDto(newUser);
        } catch (DataAccessException e) {
            throw new UserSaveException("Erro ao salvar o usuário", e);
        }
    }

    private void validateUserEmail(String email) {
        Optional<UserModel> userModel
                = userRepository.findByEmail(email);
        if (userModel.isPresent()) {
            throw new EmailAlreadyExistsException("Este Email já está cadastrado");
        }
    }

    private UserDto convertModelToDto(UserModel userModel) {
        if (userModel == null) {
            throw new IllegalArgumentException("O objeto usuário não pode ser nulo");
        }
        RoleDto roleDto = roleService
                .getRoleDtoByDescription(userModel.getRole().getDescription());
        PersonDto personDto = personService
                .getPersonDtoByDocument(userModel.getPerson().getDocument());
        UserDto userDto = new UserDto();
        userDto.setId(userModel.getId());
        userDto.setEmail(userModel.getEmail());
        userDto.setPassword(userModel.getPassword());
        userDto.setIsActive(userModel.getIsActive());
        userDto.setBalance(userModel.getBalance());
        userDto.setPerson(personDto);
        userDto.setRole(roleDto);
        return userDto;
    }

    private List<UserDto> convertListToDto(List<UserModel> list) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserModel userModel : list) {
            UserDto userDto = this.convertModelToDto(userModel);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
