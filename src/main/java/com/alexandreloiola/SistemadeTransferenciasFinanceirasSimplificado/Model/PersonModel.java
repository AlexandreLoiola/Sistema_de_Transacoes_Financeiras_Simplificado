package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Data
@Table(name = "TB_PERSONS")
public class PersonModel implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @Column(name = "document", nullable = false, unique = true)
    private String document;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

}
