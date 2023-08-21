package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "TB_USERS")
public class UserModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 256, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 11, nullable = false)
    private String password;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonModel person;

    @ManyToOne
    @JoinColumn(name = "idRole", referencedColumnName = "id")
    private RoleModel role;

}
