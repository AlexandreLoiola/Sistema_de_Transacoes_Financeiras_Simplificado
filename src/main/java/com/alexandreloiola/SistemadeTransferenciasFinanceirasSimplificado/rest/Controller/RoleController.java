package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Controller;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.RoleDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.RoleForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.RoleUpdateForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDtoList = roleService.getAllRoles();
        return ResponseEntity.ok().body(roleDtoList);
    }

    @PostMapping
    public ResponseEntity<RoleDto> insertRole(
            @Valid @RequestBody RoleForm roleForm
    ) {
        RoleDto roleDto = roleService.insertRole(roleForm);
        return ResponseEntity.ok().body(roleDto);

    }

    @PutMapping("/{description}")
    public ResponseEntity<RoleDto> updateRole(
            @Valid @RequestBody RoleUpdateForm roleUpdateForm,
            @PathVariable("description") String description
    ) {
        RoleDto roleDto = roleService.updateRole(description, roleUpdateForm);
        return ResponseEntity.ok().body(roleDto);
    }

    @DeleteMapping("/{description}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable("description") String description
    ) {
        roleService.deleteRole(description);
        return ResponseEntity.noContent().build();
    }

}