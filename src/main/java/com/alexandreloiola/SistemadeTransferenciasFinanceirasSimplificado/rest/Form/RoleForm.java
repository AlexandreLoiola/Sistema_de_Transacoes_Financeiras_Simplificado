package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleForm {

    @NotNull(message = "O campo descrição não pode estar vazio")
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(min = 3, max = 100, message = "A descrição do papel de usuário deve ter entre 3 e 100 caracteres.")
    private String description;

}
