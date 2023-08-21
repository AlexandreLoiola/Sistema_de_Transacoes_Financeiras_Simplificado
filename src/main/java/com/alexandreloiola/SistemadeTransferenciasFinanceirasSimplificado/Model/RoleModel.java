package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_ROLES")
public class RoleModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 100, nullable = false, unique = true)
    private String description;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;
}
