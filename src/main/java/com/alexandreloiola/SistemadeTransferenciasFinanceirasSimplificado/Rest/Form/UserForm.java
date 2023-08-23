package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Rest.Form;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 100)
    private String name;

    @NotEmpty
    @NotBlank(message = "O campo documento não pode ficar em branco")
    private String document;

    @NotEmpty
    @NotBlank(message = "O campo email não pode ficar vazio")
    private String email;

    @NotEmpty
    @NotBlank(message = "O campo senha não pode ficar vazio")
    @Size(min = 8, max = 50, message = "Senha do Pessoa deve ter entre 8 e 50 caracteres.")
    private String password;

    @NotNull(message = "O campo tipo de usuário não pode estar vazio")
    private String role;

}
