package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_ROLES")
public class RoleModel implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "description", length = 100, nullable = false, unique = true)
    private String description;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;
}
