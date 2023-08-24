package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_USERS")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

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
