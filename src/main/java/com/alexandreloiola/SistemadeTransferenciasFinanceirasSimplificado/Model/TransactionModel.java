package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.Model;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_TRANSACTIONS")
@Data
public class TransactionModel implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name="senderId")
    private UserModel sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private UserModel receiver;
}
