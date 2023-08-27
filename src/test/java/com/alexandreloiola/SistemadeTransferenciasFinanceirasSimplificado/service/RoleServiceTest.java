package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model.RoleModel;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Repository.RoleRepository;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.RoleDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.RoleForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.RoleUpdateForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.DataIntegrityViolationException;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.exceptions.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    RoleService roleService;

    @Mock
    RoleRepository roleRepository;

    private UUID id;
    private RoleModel roleModel;
    private RoleForm roleForm;
    private RoleUpdateForm roleUpdateForm;

    @BeforeEach
    public void setUp() {
        UUID id = UUID.randomUUID();

        roleModel = new RoleModel();
        roleModel.setId(id);
        roleModel.setDescription("Customer");
        roleModel.setIsActive(true);

        roleForm = new RoleForm();
        roleForm.setDescription("Customer");

        roleUpdateForm = new RoleUpdateForm();
        roleUpdateForm.setDescription("Merchant");
        roleUpdateForm.setIsActive(false);
    }

    @Test
    public void getRoleByDescriptionWithSuccess() {
        when(roleRepository.findByDescription("Customer"))
                .thenReturn(Optional.of(roleModel));

        RoleDto roleDto = roleService.getRoleDtoByDescription("Customer");
        assertEquals(roleModel.getDescription(), roleDto.getDescription());
    }

    @Test
    public void getAllRolesWithSuccess() {
        List<RoleModel> roleModelList = new ArrayList<>();
        RoleModel roleModel = new RoleModel();
        roleModel.setId(id);
        roleModel.setDescription("Customer");
        roleModel.setIsActive(true);
        roleModelList.add(roleModel);

        when(roleRepository.findAll()).thenReturn(roleModelList);

        List<RoleDto> roleDtoList = roleService.getAllRoles();
        assertEquals(1, roleDtoList.size());
        assertEquals("Customer", roleDtoList.get(0).getDescription());
    }

    @Test
    public void insertNewRoleWithSuccess() {
        when(roleRepository.save(any(RoleModel.class))).thenReturn(roleModel);

        RoleDto roleDto = roleService.insertRole(roleForm);
        assertEquals(roleModel.getDescription(), roleDto.getDescription());
    }

    @Test
    public void updateRoleWithSuccess() {
        when(roleRepository.findByDescription("Customer")).thenReturn(Optional.of(roleModel));

        RoleDto updatedRole = roleService.updateRole("Customer", roleUpdateForm);
        assertEquals("Merchant", updatedRole.getDescription());
    }

    @Test
    public void updateRoleNotFound() {
        when(roleRepository.findByDescription("Merchant")).thenReturn(Optional.empty());

        assertThrows(DataIntegrityViolationException.class, () -> {
            roleService.updateRole("Merchant", roleUpdateForm);
        });
    }

    @Test
    public void deleteRoleWithSuccess() {
        when(roleRepository.findByDescription("Customer")).thenReturn(Optional.of(roleModel));

        roleService.deleteRole("Customer");
    }

    @Test
    public void getRoleDtoByDescriptionNotFound() {
        when(roleRepository.findByDescription("Customer")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            roleService.getRoleModelByDescription("Customer");
        });
    }

    @Test
    public void deleteRoleNotFound() {
        when(roleRepository.findByDescription("Customer")).thenReturn(Optional.empty());

        assertThrows(DataIntegrityViolationException.class, () -> {
            roleService.deleteRole("Customer");
        });
    }

    @Test
    public void insertRoleAlreadyExists() {
        when(roleRepository.findByDescription("Customer")).thenReturn(Optional.of(roleModel));

        assertThrows(DataIntegrityViolationException.class, () -> {
            roleService.insertRole(roleForm);
        });
    }
}