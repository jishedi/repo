package com.wexinc.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    @SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    @Column(name = "transaction_id", nullable = false)
    @JsonProperty("transactionId")
    private Long transactionId;

    @Size(max = 50)
    @NotNull
    @Getter
    @Setter
    @Column(name = "purchase_description", nullable = false, length = 50)
    @JsonProperty("purchaseDescription")
    private String purchaseDescription;

    @NotNull
    @Getter
    @Setter
    @Column(name = "transaction_date", nullable = false)
    @JsonProperty("transactionDate")
    private Instant transactionDate;

    @NotNull
    @Getter
    @Setter
    @Column(name = "purchase_amount", nullable = false)
    @JsonProperty("purchaseAmount")
    private Double purchaseAmount;

    @NotNull
    @Getter
    @Setter
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
