package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Rest.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String description;
    private Boolean isActive;
}
