package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Rest.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PersonForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 100)
    private String name;

    @NotEmpty
    @NotBlank(message = "O campo documento não pode ficar em branco")
    private String document;

}
