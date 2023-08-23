package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Rest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String password;

    private BigDecimal balance;

    private Boolean isActive;

    private PersonDto person;
    private RoleDto role;
}
