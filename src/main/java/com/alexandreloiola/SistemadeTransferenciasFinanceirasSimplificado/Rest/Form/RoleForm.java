package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Rest.Form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleForm {

    @NotNull(message = "O campo descrição não pode estar vazio")
    private String description;

}
