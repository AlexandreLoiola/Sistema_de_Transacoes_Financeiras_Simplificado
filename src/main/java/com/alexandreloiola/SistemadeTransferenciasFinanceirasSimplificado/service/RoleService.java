package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.RoleModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository.RoleRepository;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.RoleDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.RoleForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.RoleUpdateForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.DataIntegrityViolationException;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDto> getAllRoles() {
        List<RoleModel> roleModelList = roleRepository.findAll();
        return convertListToDto(roleModelList);
    }

    public RoleDto getRoleDtoByDescription(String description) {
        Optional<RoleModel> roleModel = roleRepository.findByDescription(description);
        if (!roleModel.isPresent()) {
            throw new NoSuchElementException("O papel de usário não foi encontrado");
        }
        return convertModelToDto(roleModel.get());
    }

    public RoleModel getRoleModelByDescription(String description) {
        Optional<RoleModel> roleModel = roleRepository.findByDescription(description);
        if (!roleModel.isPresent()) {
            throw new NoSuchElementException("O papel de usário não foi encontrado");
        }
        return roleModel.get();
    }

    @Transactional
    public RoleDto insertRole(RoleForm roleForm) {
        Optional<RoleModel> roleModel = roleRepository.findByDescription(roleForm.getDescription());
        if (roleModel.isPresent()) {
            throw new DataIntegrityViolationException("Papel de usuário já cadastrado!");
        }
        RoleModel newRole = new RoleModel();
        newRole.setDescription(roleForm.getDescription());
        newRole.setIsActive(true);
        newRole = roleRepository.save(newRole);
        return convertModelToDto(newRole);
    }

    @Transactional
    public RoleDto updateRole(String description, RoleUpdateForm roleUpdateForm) {
        Optional<RoleModel> roleModel = roleRepository.findByDescription(description);
        if (!roleModel.isPresent()) {
            throw new DataIntegrityViolationException("Papel de usuário não cadastrado!");
        }
        try {
            RoleModel updateRole = roleModel.get();
            updateRole.setDescription(roleUpdateForm.getDescription());
            updateRole.setIsActive(roleUpdateForm.getIsActive());
            roleRepository.save(updateRole);
            return convertModelToDto(updateRole);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Ocorreu um erro ao tentar alterar o papel de usuário. Por favor, tente novamente mais tarde."
            );
        }
    }

    @Transactional
    public void deleteRole(String description) {
        Optional<RoleModel> roleModel = roleRepository.findByDescription(description);
        if (!roleModel.isPresent()) {
            throw new DataIntegrityViolationException("Papel de usuário não cadastrado!");
        }
        try {
            roleRepository.delete(roleModel.get());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    "Ocorreu um erro ao tentar excluir o papel de usuário. Por favor, tente novamente mais tarde"
            );
        }
    }

    private RoleDto convertModelToDto(RoleModel roleModel) {
        if (roleModel == null) {
            throw new IllegalArgumentException("O objeto papel de usuário não pode ser nulo");
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setDescription(roleModel.getDescription());
        roleDto.setIsActive(roleModel.getIsActive());
        return roleDto;
    }

    private List<RoleDto> convertListToDto(List<RoleModel> list) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleModel roleModel : list) {
            RoleDto roleDto = this.convertModelToDto(roleModel);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }
}